package com.kelvin.githubapiapp.favorite.state

import com.kelvin.githubapiapp.model.UserUIModel

data class FavoriteState(
    val isLoading: Boolean = false,
    val data: List<UserUIModel> = listOf(),
    val error: String? = null
)
