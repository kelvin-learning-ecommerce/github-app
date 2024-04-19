package com.kelvin.githubapiapp.domain.usecase

import com.kelvin.githubapiapp.data.model.UserDetailModel
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel
import com.kelvin.githubapiapp.domain.repository.FavoriteRepository
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, Flow<Resource<List<FavoriteDaoModel>>>>() {
    override fun execute(params: Unit): Flow<Resource<List<FavoriteDaoModel>>> {
        return favoriteRepository.getAll()
    }
}
