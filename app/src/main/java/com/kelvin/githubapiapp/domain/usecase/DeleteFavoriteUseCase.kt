package com.kelvin.githubapiapp.domain.usecase

import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Int, Unit>() {
    override fun execute(params: Int) {
        return favoriteRepository.deleteByMovieId(params)
    }
}
