package com.kelvin.githubapiapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel

@Database(entities = [FavoriteDaoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
