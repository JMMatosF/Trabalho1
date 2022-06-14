package com.example.trabalho1.login
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabalho1.R

class LoginViewModel: ViewModel() {

    private val loginResult = MutableLiveData<LoginUiState>()
    val loginResultLiveData: LiveData<LoginUiState> = loginResult

    fun validateInput(username: String, password: String) {
        if(username.isEmpty()) {
            loginResult.postValue(LoginUiState.Error(R.string.username_error))
        } else if(password.isEmpty() || password.length < 4) {
            loginResult.postValue(LoginUiState.Error(R.string.password_error))
        } else {
            loginResult.postValue(LoginUiState.Success)
        }
    }
}