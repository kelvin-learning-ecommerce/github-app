package com.kelvin.githubapiapp.domain.repository

import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAll(): Flow<Resource<List<FavoriteDaoModel>>>
    fun insertMovie(vararg movie: FavoriteDaoModel)
    fun deleteByMovieId(id: Int)


}
