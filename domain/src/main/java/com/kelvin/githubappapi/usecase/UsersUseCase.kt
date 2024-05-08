package com.kelvin.githubappapi.usecase

import com.kelvin.githubapiapp.model.UserModel
import com.kelvin.githubapiapp.query.UserListQuery
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UserListQuery, Flow<Resource<List<UserModel>>>>() {
    override fun execute(params: UserListQuery): Flow<Resource<List<UserModel>>> {
        return userRepository.getUserList(params)
    }
}
