package com.kelvin.githubapiapp.di

import android.content.Context
import androidx.room.Room
import com.kelvin.githubapiapp.data.room.AppDatabase
import com.kelvin.githubapiapp.data.room.repository.FavoriteRepositoryImpl
import com.kelvin.githubapiapp.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "github_kelvin_app_db")
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
