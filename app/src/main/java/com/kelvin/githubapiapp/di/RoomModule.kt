package com.kelvin.githubapiapp.di

import android.content.Context
import androidx.room.Room
import com.kelvin.githubappapi.AppDatabase
import com.kelvin.githubappapi.impl.FavoriteRepositoryImpl
import com.kelvin.githubappapi.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DB_NAME = "github_kelvin_app_db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDbProductRepository(db: AppDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(db)
    }
}
