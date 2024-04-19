package com.kelvin.githubapiapp.data.query

data class UserListQuery(
    val per_page: Int = 10, var since: Int = 0
)
