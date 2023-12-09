package com.jda.randomuasertest.data.network

import com.jda.randomuasertest.data.network.model.Results
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://randomuser.me"

interface UserApiService {
    @GET("api")
    suspend fun getUsersList(
        @Query("results") result: Int
    ): Results
}

object UserApiServiceFactory {
    fun makeUserApiService(): UserApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UserApiService::class.java)
    }
}