package com.example.trabalho1.overview.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @ColumnInfo(name = "author_name") val authorName: String?,
    @ColumnInfo(name = "book_name") val bookName: String?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}
