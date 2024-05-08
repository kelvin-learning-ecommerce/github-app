package com.kelvin.githubappapi.repository

import com.kelvin.githubapiapp.room.FavoriteDaoModel
import com.kelvin.githubapiapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAll(): Flow<Resource<List<FavoriteDaoModel>>>
    fun insertMovie(movie: FavoriteDaoModel)
    fun deleteByMovieId(id: Int)


}
