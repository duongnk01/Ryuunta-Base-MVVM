package base.ryuunta.base_mvvm

import base.ryuunta.base_mvvm.core.base.BaseActivity
import base.ryuunta.base_mvvm.databinding.ActivityInitiationBinding

class InitiationActivity :
    BaseActivity<ActivityInitiationBinding, InitiationViewModel>(InitiationViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_initiation
    }

    override fun initViews() {

    }

    override fun initEvents() {
        //TODO("Not yet implemented")
    }

}