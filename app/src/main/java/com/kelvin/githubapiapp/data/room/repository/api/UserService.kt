package com.kelvin.githubapiapp.data.room.repository.api

import com.kelvin.githubapiapp.data.model.UserModel
import com.kelvin.githubapiapp.shared.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject

interface UsersApi {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") page: Int? = 10,
        @Query("since") since: Int? = 0
    ): Response<List<UserModel>>
}

class UserService @Inject constructor(private val api: UsersApi) {
    fun getUsersList(perPage: Int, since: Int): Flow<Resource<List<UserModel>>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getUsers(
                since = since
            )

            emit(Resource.Success(genre.body()))
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
}
