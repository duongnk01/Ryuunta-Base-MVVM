package base.ryuunta.base_mvvm.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import base.ryuunta.base_mvvm.InitiationViewModel

open class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InitiationViewModel::class.java)) {
            return InitiationViewModel() as T
        }

        return super.create(modelClass)

    }
}