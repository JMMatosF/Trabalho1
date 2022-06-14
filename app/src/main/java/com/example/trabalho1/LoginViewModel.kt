package com.example.trabalho1
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class LoginViewModel: ViewModel() {

    private val loginresult = MutableLiveData<Boolean>()
    val loginResultLiveData = loginresult


    fun valido(username: String, password:String) {


        loginResultLiveData.postValue(username==password)

    }
}