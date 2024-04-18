package com.kelvin.githubapiapp.di

import com.kelvin.githubapiapp.data.room.repository.UserRepositoryImpl
import com.kelvin.githubapiapp.data.room.repository.api.UserService
import com.kelvin.githubapiapp.data.room.repository.api.UsersApi
import com.kelvin.githubapiapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object DaggerHiltModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client =

            OkHttpClient.Builder().apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header(
                            "Authorization",
                            "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"
                        )

                        return@Interceptor chain.proceed(builder.build())
                    }
                )
                addInterceptor(interceptor)
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Games
    @Provides
    @ViewModelScoped
    fun provideMovieApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieService(userApi: UsersApi): UserService {
        return UserService(userApi)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(userService: UserService): UserRepository {
        return UserRepositoryImpl(userService)
    }
}
