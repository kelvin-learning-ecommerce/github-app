package com.kelvin.githubapiapp.domain.usecase

import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<FavoriteDaoModel, Unit>() {
    override fun execute(params: FavoriteDaoModel) {
        return favoriteRepository.insertMovie(params)
    }
}
