package com.jda.randomuasertest.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jda.randomuasertest.domain.model.RandomUser
import com.jda.randomuasertest.ui.entities.User
import com.jda.randomuasertest.ui.entities.getUsers

@Composable
fun UserList(onUserClick: (User) -> Unit, modifier: Modifier, users: List<User>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 35.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.background(Color.White)
    ) {
        items(users) { user ->
            UserItem(
                onClick = { onUserClick(user) },
                user = user)
        }
    }
}

@Preview
@Composable
fun UserListPreview() {
    val users = getUsers()
    UsersApp {
        UserList(
            onUserClick = { },
            modifier = Modifier,
            users = users
        )
    }
}