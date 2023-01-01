package uz.akfadiler.testappaliftech.presentation.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.akfadiler.testappaliftech.data.remote.response.user.GeoUser
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import uz.akfadiler.testappaliftech.domain.model.asSuccess
import uz.akfadiler.testappaliftech.domain.model.onSuccess
import uz.akfadiler.testappaliftech.domain.model.onText
import uz.akfadiler.testappaliftech.domain.usecase.GetUserListUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val userListUseCase: GetUserListUseCase
) : ViewModel(), HomeViewModel {
    override val errorLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override val progressLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    override val loadLiveData: MutableLiveData<List<UserResponse>> by lazy { MutableLiveData<List<UserResponse>>() }
    override val openNextScreenLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    override val locationLiveData: MutableLiveData<GeoUser> by lazy { MutableLiveData<GeoUser>() }
    override val phoneLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        load()
    }

    override fun load() {
        progressLiveData.value = true
        userListUseCase.invoke().onEach {
            progressLiveData.value = false
            it.onSuccess {
                val data = it.asSuccess.data
                loadLiveData.value = data
            }
            it.onText {
                errorLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }

    override fun location(location: GeoUser) {
        locationLiveData.value = location
    }

    override fun phone(phoneNumber: String) {
        phoneLiveData.value = phoneNumber
    }

    override fun posts(userId: Int) {
        openNextScreenLiveData.value = userId
    }

}