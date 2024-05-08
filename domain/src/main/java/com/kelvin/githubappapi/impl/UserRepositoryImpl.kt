package com.kelvin.githubappapi.impl

import com.kelvin.githubapiapp.model.UserDetailModel
import com.kelvin.githubapiapp.model.UserModel
import com.kelvin.githubapiapp.query.UserListQuery
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.impl.api.UserService
import com.kelvin.githubappapi.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val movieService: UserService) :
    UserRepository {
    override fun getUserList(params: UserListQuery): Flow<Resource<List<UserModel>>> =
        movieService.getUsersList(params.per_page, params.since, params.search)

    override fun getUserDetail(id: Int): Flow<Resource<UserDetailModel>> =
        movieService.getUserDetail(id)
}
