package com.jda.randomuasertest.ui.screens.main

import com.jda.randomuasertest.ui.entities.User

data class MainState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isSearchBarVisible: Boolean = false,
    val searchText: String = "",
)
