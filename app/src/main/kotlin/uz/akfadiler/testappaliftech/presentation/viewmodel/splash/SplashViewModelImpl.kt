package uz.akfadiler.testappaliftech.presentation.viewmodel.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : ViewModel(), SplashViewModel {
    override val openNextScreenLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            openNextScreenLiveData.postValue(Unit)
        }
    }

}