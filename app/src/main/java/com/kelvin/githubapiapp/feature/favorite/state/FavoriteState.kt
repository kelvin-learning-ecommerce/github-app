package com.kelvin.githubapiapp.feature.favorite.state

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.data.model.UserUIModel


data class FavoriteState(
    val isLoading: Boolean = false,
    val data: List<UserUIModel> = listOf(),
    val error: String = ""
)
