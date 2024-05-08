package com.kelvin.githubappapi

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kelvin.githubapiapp.room.FavoriteDaoModel
import com.kelvin.githubappapi.FavoriteDao

@Database(entities = [FavoriteDaoModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
