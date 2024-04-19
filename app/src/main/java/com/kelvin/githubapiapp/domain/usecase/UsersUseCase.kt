package com.kelvin.githubapiapp.domain.usecase

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.domain.repository.UserRepository
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UserListQuery, Flow<Resource<List<UserModel>>>>() {
    override fun execute(params: UserListQuery): Flow<Resource<List<UserModel>>> {
        return userRepository.getUserList(params)
    }
}
