package com.kelvin.githubapiapp.userdetail.state

import com.kelvin.githubapiapp.model.UserDetailModel


data class UserDetailState(
    val isLoading: Boolean = false,
    val data: UserDetailModel? = UserDetailModel(),
    val error: String? = null
)
