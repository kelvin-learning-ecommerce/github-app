package com.kelvin.githubapiapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteDaoModel(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo("id") var id: Int? = null,
    @ColumnInfo("login") var login: String? = null,
    @ColumnInfo("repo_url") var repoUrl: String? = null,
    @ColumnInfo("avatarUrl") var avatarUrl: String? = null,
)
