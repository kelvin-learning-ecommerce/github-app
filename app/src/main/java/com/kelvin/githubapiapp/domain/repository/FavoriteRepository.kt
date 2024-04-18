package com.kelvin.githubapiapp.domain.repository

import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel

interface FavoriteRepository {
    fun getAll(): List<FavoriteDaoModel>
    fun loadAllByIds(userIds: IntArray): List<FavoriteDaoModel>
    fun insertMovie(vararg movie: FavoriteDaoModel)
    fun delete(user: FavoriteDaoModel)
    fun deleteByMovieId(id: Int)


}
