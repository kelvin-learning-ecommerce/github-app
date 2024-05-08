package com.kelvin.githubappapi.impl

import com.kelvin.githubapiapp.room.FavoriteDaoModel
import com.kelvin.githubapiapp.utils.Resource
import com.kelvin.githubappapi.AppDatabase
import com.kelvin.githubappapi.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    FavoriteRepository {
    override fun getAll(): Flow<Resource<List<FavoriteDaoModel>>> =
        flow {
            emit(Resource.Loading())
            try {
                val data = appDatabase.favoriteDao().getAll()

                emit(Resource.Success(data))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!"
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection."
                    )
                )
            }
        }

    override fun insertMovie(movie: FavoriteDaoModel) {
        return appDatabase.favoriteDao().insertMovie(movie = movie)

    }

    override fun deleteByMovieId(id: Int) {
        return appDatabase.favoriteDao().deleteByMovieId(id)
    }
}
