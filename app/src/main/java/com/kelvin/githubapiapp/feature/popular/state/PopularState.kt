package com.kelvin.githubapiapp.feature.popular.state

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.model.UserUIModel


data class PopularState(
    val isLoading: Boolean = false,
    val data: List<UserUIModel> = listOf(),
    val error: String = ""
)
