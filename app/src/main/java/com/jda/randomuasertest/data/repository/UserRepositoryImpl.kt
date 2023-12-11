package com.jda.randomuasertest.data.repository

import com.jda.randomuasertest.data.Result
import com.jda.randomuasertest.data.remote.UserApiService
import com.jda.randomuasertest.data.remote.dto.ResultsDTO
import com.jda.randomuasertest.data.remote.dto.asDomainModel
import com.jda.randomuasertest.domain.model.RandomUser
import com.jda.randomuasertest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApiService
): UserRepository {
    override fun getUsers(page: Int): Flow<Result<List<RandomUser>>> = flow {
        emit(Result.Loading())
        try {
            val response = api.getUsersList(page, 20).asDomainModel()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(
                message = e.message,
                data = null
            ))
        }
    }
}