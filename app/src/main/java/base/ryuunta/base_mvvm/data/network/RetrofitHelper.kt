package base.ryuunta.base_mvvm.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import base.ryuunta.base_mvvm.data.network.UrlManage.BASE_URL

object RetrofitHelper {

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: RetrofitService = getInstance().create(RetrofitService::class.java)
}