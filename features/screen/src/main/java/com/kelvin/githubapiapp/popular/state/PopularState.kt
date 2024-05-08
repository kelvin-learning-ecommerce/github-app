package com.kelvin.githubapiapp.popular.state

import com.kelvin.githubapiapp.model.UserUIModel


data class PopularState(
    val isLoading: Boolean = false,
    val data: List<UserUIModel> = listOf(),
    val error: String? = ""
)
