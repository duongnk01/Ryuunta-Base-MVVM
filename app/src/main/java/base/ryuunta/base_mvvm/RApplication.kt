package base.ryuunta.base_mvvm

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.net.wifi.WifiManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import base.ryuunta.base_mvvm.core.base.Config
import base.ryuunta.base_mvvm.preference.ThemePreference
import base.ryuunta.base_mvvm.utils.setDarkModeTheme

class RApplication : Application() {
    var isDarkMode = false
    private lateinit var app: RApplication

    private var mBroadcastData: MutableLiveData<String>? = null

    private var mCacheMap: MutableMap<String, Any>? = null

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action ?: return
            when (action) {
                WifiManager.NETWORK_STATE_CHANGED_ACTION, LocationManager.PROVIDERS_CHANGED_ACTION -> mBroadcastData!!.setValue(
                    action
                )
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        mCacheMap = HashMap()
        mBroadcastData = MutableLiveData()
        val filter = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION)
        registerReceiver(mReceiver, filter)

        //dark mode support
        ThemePreference.getThemePreferenceData(
            this,
            onError = {
            //handler when get data error
        },
            onComplete = { themePreference ->
                isDarkMode = themePreference.isDarkMode
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                setDarkModeTheme(isDarkMode)
                Config.isDarkMode = isDarkMode
            }
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(mReceiver)
    }

    fun getInstance(): RApplication = app

    fun observeBroadcast(owner: LifecycleOwner?, observer: Observer<String>?) {
        mBroadcastData!!.observe(owner!!, observer!!)
    }

    fun observeBroadcastForever(observer: Observer<String>?) {
        mBroadcastData!!.observeForever(observer!!)
    }

    fun removeBroadcastObserver(observer: Observer<String>?) {
        mBroadcastData!!.removeObserver(observer!!)
    }

    fun putCache(key: String, value: Any) {
        mCacheMap!![key] = value
    }

    fun takeCache(key: String): Any? {
        return mCacheMap!!.remove(key)
    }
}