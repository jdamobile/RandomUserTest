package com.jda.randomuasertest.domain

import com.jda.randomuasertest.data.network.model.asDomainModel
import com.jda.randomuasertest.data.repository.UserRepository
import com.jda.randomuasertest.domain.model.RandomUser

class GetUsersUseCase {
    private val userRepo = UserRepository()

    suspend operator fun invoke(): List<RandomUser> {
        return userRepo.getUser().asDomainModel()
    }
}