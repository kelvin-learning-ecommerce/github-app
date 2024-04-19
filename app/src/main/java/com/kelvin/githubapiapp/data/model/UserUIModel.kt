package com.kelvin.githubapiapp.data.model

data class UserUIModel(
    var id: Int,
    var login: String,
    var avatarUrl: String,
    var isFavorite: Boolean = false

)

fun List<UserUIModel>.updateIsFavorite(id: Int, isFav: Boolean): List<UserUIModel> =
    this.map { worker -> if (worker.id == id) worker.copy(isFavorite = isFav) else worker }
