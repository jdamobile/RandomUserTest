package com.jda.randomuasertest.data.remote

import com.jda.randomuasertest.data.remote.dto.ResultsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("api")
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Query("results") result: Int
    ): ResultsDTO
}
