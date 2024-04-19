package com.kelvin.githubapiapp.feature.popular.state

import com.kelvin.githubapiapp.data.model.UserModel


data class PopularState(
    val isLoading: Boolean = false,
    val data: List<UserModel> = listOf(),
    val error: String = ""
)
