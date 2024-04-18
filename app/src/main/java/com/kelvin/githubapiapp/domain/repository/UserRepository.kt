package com.kelvin.githubapiapp.domain.repository

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.query.UserListQuery
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserList(
        params: UserListQuery
    ): Flow<Resource<List<UserModel>>>

}
