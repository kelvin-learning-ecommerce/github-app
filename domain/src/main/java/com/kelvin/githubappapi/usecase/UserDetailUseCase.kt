package com.kelvin.githubappapi.usecase

import com.kelvin.githubapiapp.model.UserDetailModel
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Int, Flow<Resource<UserDetailModel>>>() {
    override fun execute(params: Int): Flow<Resource<UserDetailModel>> {
        return userRepository.getUserDetail(params)
    }
}
