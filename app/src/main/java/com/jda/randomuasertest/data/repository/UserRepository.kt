package com.jda.randomuasertest.data.repository

import com.jda.randomuasertest.data.network.UserApiServiceFactory
import com.jda.randomuasertest.data.network.model.Results

class UserRepository {
    suspend fun getUser(): Results = UserApiServiceFactory.makeUserApiService().getUsersList(20)
}