package com.kelvin.githubappapi.usecase

import com.kelvin.githubappapi.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Int, Unit>() {
    override fun execute(params: Int) {
        return favoriteRepository.deleteByMovieId(params)
    }
}
