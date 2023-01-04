package uz.akfadiler.testappaliftech.presentation.viewmodel.home

import androidx.lifecycle.LiveData
import uz.akfadiler.testappaliftech.data.remote.response.user.GeoUser
import uz.akfadiler.testappaliftech.data.remote.response.user.UserResponse
import uz.akfadiler.testappaliftech.domain.model.UserData

interface HomeViewModel {
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadLiveData: LiveData<List<UserData>>
    val openNextScreenLiveData: LiveData<Int>
    val locationLiveData: LiveData<GeoUser>
    val phoneLiveData: LiveData<String>

    fun load()
    fun location(location : GeoUser)
    fun phone(phoneNumber : String)
    fun posts(userId : Int)
}