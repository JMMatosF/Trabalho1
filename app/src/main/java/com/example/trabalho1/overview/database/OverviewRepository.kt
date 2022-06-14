package com.example.trabalho1.overview.database

import android.content.Context

class OverviewRepository(private val context: Context) {

    private val db = OverviewDatabase.getInstance(context).getOverviewDao()

    suspend fun getAllBooks() = db.getAllBooks()

    suspend fun insertBook(bookEntity: BookEntity) = db.insertBook(bookEntity)

    suspend fun deleteBook(bookEntity: BookEntity) = db.deleteBook(bookEntity)
}