package com.kelvin.githubapiapp.feature.popular.state


data class PopularState(
    val isLoading: Boolean = false,
//    val data: ImmutableList<PhotoUIModel> = persistentListOf(),
    val error: String = ""
    )
