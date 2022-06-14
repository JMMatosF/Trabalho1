package com.example.trabalho1.overview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class OverviewDatabase : RoomDatabase() {

    abstract fun getOverviewDao(): OverviewDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: OverviewDatabase? = null

        fun getInstance(context: Context): OverviewDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): OverviewDatabase =
            Room.databaseBuilder(context, OverviewDatabase::class.java, "overviewDB")
                .build()
    }
}