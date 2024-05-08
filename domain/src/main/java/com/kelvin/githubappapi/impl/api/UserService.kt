package com.kelvin.githubappapi.impl.api

import com.kelvin.githubapiapp.model.UserModel
import com.kelvin.githubapiapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject

interface UsersApi {
    @GET("users/{q}")
    suspend fun getUsers(
        @Path("q") q: String? = null,
        @Query("per_page") page: Int? = 10,
        @Query("since") since: Int? = 0,
    ): Response<UserModel>

    @GET("users")
    suspend fun getUsers(
        @Query("per_page") page: Int? = 10,
        @Query("since") since: Int? = 0,
    ): Response<List<com.kelvin.githubapiapp.model.UserModel>>

    @GET("user/{id}")
    suspend fun getUserDetail(
        @Path("id") id: Int? = 0
    ): Response<com.kelvin.githubapiapp.model.UserDetailModel>
}

class UserService @Inject constructor(private val api: UsersApi) {
    fun getUsersList(perPage: Int, since: Int, search: String?): Flow<Resource<List<com.kelvin.githubapiapp.model.UserModel>>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = mutableListOf<com.kelvin.githubapiapp.model.UserModel>()
                if (search == null) {
                    val res = api.getUsers(
                        page = perPage,
                        since = since,
                    )
                    res.body()?.let { result.addAll(it) }

                } else {
                    val res = api.getUsers(
                        page = perPage,
                        since = since,
                        q = search
                    )
                    res.body()?.let { result.add(it) }

                }

                emit(Resource.Success(result))
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

    fun getUserDetail(id: Int): Flow<Resource<com.kelvin.githubapiapp.model.UserDetailModel>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getUserDetail(
                id = id
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
