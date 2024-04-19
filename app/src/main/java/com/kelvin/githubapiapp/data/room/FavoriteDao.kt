package com.kelvin.githubapiapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoritedaomodel")
    fun getAll(): List<FavoriteDaoModel>

    @Insert
    fun insertMovie(vararg movie: FavoriteDaoModel)

    @Query("DELETE FROM favoritedaomodel WHERE id = :id")
    fun deleteByMovieId(id: Int)
}
