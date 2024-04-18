package com.kelvin.githubapiapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteDao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
