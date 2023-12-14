package com.jda.randomuasertest.domain.use_case

import com.jda.randomuasertest.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock


class GetUsersUseCaseTest {

    @Test
    fun `Invoke calls user repository`(): Unit = runBlocking {
        val userRepository = mock<UserRepository>()
        val getUsersUseCase = GetUsersUseCase(userRepository)
        val page = 1

        getUsersUseCase(page)

        verify(userRepository).getUsers(page)
    }
}