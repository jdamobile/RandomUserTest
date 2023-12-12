package com.jda.randomuasertest.ui.screens.details

import com.jda.randomuasertest.ui.entities.User

data class DetailState(
    val isLoading: Boolean = false,
    val user: User
)
