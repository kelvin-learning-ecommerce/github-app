package com.kelvin.githubapiapp.domain.usecase

import com.kelvin.githubapiapp.data.model.UserDetailModel
import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.domain.repository.UserRepository
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Int, Flow<Resource<UserDetailModel>>>() {
    override fun execute(params: Int): Flow<Resource<UserDetailModel>> {
        return userRepository.getUserDetail(params)
    }
}
