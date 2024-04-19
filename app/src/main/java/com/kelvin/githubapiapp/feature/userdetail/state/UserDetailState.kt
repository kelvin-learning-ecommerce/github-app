package com.kelvin.githubapiapp.feature.userdetail.state

import com.kelvin.githubapiapp.data.model.UserDetailModel


data class UserDetailState(
    val isLoading: Boolean = false,
    val data: UserDetailModel? = UserDetailModel(),
    val error: String = ""
)
