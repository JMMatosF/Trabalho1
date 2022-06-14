package com.example.trabalho1.overview.database

import androidx.room.*

@Dao
interface OverviewDao {

    @Query("SELECT * from books")
    fun getAllBooks() : List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookEntity: BookEntity) : Long

    @Delete
    fun deleteBook(bookEntity: BookEntity) : Int
}