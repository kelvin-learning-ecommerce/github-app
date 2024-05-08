package com.kelvin.githubappapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kelvin.githubapiapp.room.FavoriteDaoModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table")
    fun getAll(): List<FavoriteDaoModel>

    @Insert
    fun insertMovie(movie: FavoriteDaoModel)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    fun deleteByMovieId(id: Int)
}
