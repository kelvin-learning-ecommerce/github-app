package com.kelvin.githubappapi.usecase

import com.kelvin.githubapiapp.room.FavoriteDaoModel
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, Flow<Resource<List<FavoriteDaoModel>>>>() {
    override fun execute(params: Unit): Flow<Resource<List<FavoriteDaoModel>>> {
        return favoriteRepository.getAll()
    }
}
