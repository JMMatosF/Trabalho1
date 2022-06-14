package com.example.trabalho1.login

import androidx.annotation.StringRes

sealed class LoginUiState {

    object Success : LoginUiState()
    data class Error(@StringRes val error: Int) : LoginUiState()
}
