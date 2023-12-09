package com.jda.randomuasertest.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jda.randomuasertest.ui.entities.User
import com.jda.randomuasertest.ui.theme.RandomUaserTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onItemClick: (User) -> Unit, users: List<User>) {

    UsersApp {
        Scaffold(
            topBar = { MainAppBar(title = "CONTACTOS", onBackPressed = { }) }
        ) { padding ->
//            val users = getUsers()
            UserList(
                onUserClick =  onItemClick,
                modifier = Modifier.padding(padding),
                users = users
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomUaserTestTheme {
        // UserList()
    }
}