package com.jda.randomuasertest.ui.screens.main

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.jda.randomuasertest.ui.theme.RandomUaserTestTheme

@Composable
fun UsersApp(content: @Composable () -> Unit) {
    RandomUaserTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}