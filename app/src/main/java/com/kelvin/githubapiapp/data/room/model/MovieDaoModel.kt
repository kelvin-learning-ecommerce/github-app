package com.kelvin.githubapiapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDaoModel(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo("id") var id: Int? = null,
)
