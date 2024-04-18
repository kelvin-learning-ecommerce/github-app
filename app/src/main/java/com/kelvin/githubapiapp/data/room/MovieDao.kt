package com.kelvin.githubapiapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kelvin.githubapiapp.data.room.model.FavoriteDaoModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoritedaomodel")
    fun getAll(): List<FavoriteDaoModel>

    @Query("SELECT * FROM favoritedaomodel WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<FavoriteDaoModel>

    @Insert
    fun insertMovie(vararg movie: FavoriteDaoModel)

    @Delete
    fun delete(user: FavoriteDaoModel)

    @Query("DELETE FROM favoritedaomodel WHERE id = :id")
    fun deleteByMovieId(id: Int)
}
