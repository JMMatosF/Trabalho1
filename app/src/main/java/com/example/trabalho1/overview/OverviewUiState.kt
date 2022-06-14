package com.example.trabalho1.overview

import androidx.annotation.StringRes
import com.example.trabalho1.overview.database.BookEntity

sealed class OverviewUiState {

    data class Success(val books : List<BookEntity>) : OverviewUiState()
    object Loading : OverviewUiState()
    data class Error(@StringRes val resId: Int) : OverviewUiState()
}