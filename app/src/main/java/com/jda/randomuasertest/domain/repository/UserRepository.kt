package com.jda.randomuasertest.domain.repository

import com.jda.randomuasertest.data.Result
import com.jda.randomuasertest.domain.model.RandomUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(page: Int): Flow<Result<List<RandomUser>>>
}