package com.kelvin.githubapiapp.data.room.repository

import com.kelvin.githubapiapp.data.room.AppDatabase
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    FavoriteRepository {
    override fun getAll(): List<FavoriteDaoModel> {
        return appDatabase.favoriteDao().getAll()
    }

    override fun loadAllByIds(userIds: IntArray): List<FavoriteDaoModel> {
        return appDatabase.favoriteDao().loadAllByIds(userIds)

    }

    override fun insertMovie(vararg movie: FavoriteDaoModel) {
        return appDatabase.favoriteDao().insertMovie(movie = movie)

    }

    override fun delete(user: FavoriteDaoModel) {
        return appDatabase.favoriteDao().delete(user)

    }

    override fun deleteByMovieId(id: Int) {
        return appDatabase.favoriteDao().deleteByMovieId(id)
    }
}
