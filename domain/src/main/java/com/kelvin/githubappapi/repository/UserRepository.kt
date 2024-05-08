package com.kelvin.githubappapi.repository

import com.kelvin.githubapiapp.model.UserDetailModel
import com.kelvin.githubapiapp.model.UserModel
import com.kelvin.githubapiapp.query.UserListQuery
import com.kelvin.githubapiapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(
        params: UserListQuery
    ): Flow<Resource<List<UserModel>>>
    fun getUserDetail(
        id: Int
    ): Flow<Resource<UserDetailModel>>

}
