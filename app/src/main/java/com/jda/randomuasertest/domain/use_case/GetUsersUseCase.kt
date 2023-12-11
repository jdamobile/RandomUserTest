package com.jda.randomuasertest.domain.use_case

import com.jda.randomuasertest.data.Result
import com.jda.randomuasertest.domain.model.RandomUser
import com.jda.randomuasertest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepo: UserRepository
) {
    operator fun invoke(page: Int): Flow<Result<List<RandomUser>>> {
        return userRepo.getUsers(page)
    }
}