package com.kelvin.githubapiapp.data.room.repository

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.data.room.repository.api.UserService
import com.kelvin.githubapiapp.domain.repository.UserRepository
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val movieService: UserService) :
    UserRepository {

    override fun getUserList(params: UserListQuery): Flow<Resource<List<UserModel>>> =
        movieService.getUsersList(params.page, params.since);
}
