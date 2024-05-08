package com.kelvin.githubappapi.usecase

import com.kelvin.githubapiapp.room.FavoriteDaoModel
import com.kelvin.githubappapi.repository.FavoriteRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<FavoriteDaoModel, Unit>() {
    override fun execute(params: FavoriteDaoModel) {
        return favoriteRepository.insertMovie(params)
    }
}
