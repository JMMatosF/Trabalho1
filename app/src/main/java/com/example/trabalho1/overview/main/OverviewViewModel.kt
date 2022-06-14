package com.example.trabalho1.overview.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalho1.R
import com.example.trabalho1.overview.database.BookEntity
import com.example.trabalho1.overview.database.OverviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val observable = MutableLiveData<OverviewUiState>()

    fun getUiObservable() : LiveData<OverviewUiState> = observable

    private var repository: OverviewRepository? = null

    fun initScreen(context: Context) {
        repository = OverviewRepository(context)
    }

    fun getAllBooks() {
        observable.postValue(OverviewUiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            repository?.getAllBooks().let { list ->
                list?.let {
                    when {
                        it.isNotEmpty() -> {
                            observable.postValue(OverviewUiState.Success(it))
                        }
                        it.isEmpty() -> {
                            observable.postValue(OverviewUiState.Error(R.string.books_list_empty))
                        }
                        else -> {
                            observable.postValue(OverviewUiState.Error(R.string.books_list_error))
                        }
                    }
                } ?: observable.postValue(OverviewUiState.Error(R.string.books_list_error))
            }

        }
    }

    fun insertBook(bookEntity: BookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            if(repository?.insertBook(bookEntity) != 1L) getAllBooks()
            else observable.postValue(OverviewUiState.Error(R.string.books_list_error))
        }

    }

    fun deleteBook(bookEntity: BookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            if(repository?.deleteBook(bookEntity) == 1) getAllBooks()
            else observable.postValue(OverviewUiState.Error(R.string.books_list_error))
        }

    }
}