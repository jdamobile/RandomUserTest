package com.jda.randomuasertest.ui.screens.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jda.randomuasertest.R
import com.jda.randomuasertest.ui.theme.Oswald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(color: Color = Color.Black, title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = Oswald,
                fontWeight = FontWeight.Normal,
                color = color
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "back",
                    tint = color
                )
            }
        },
        actions = {
            AppBarAction(
                resource = painterResource(id = R.drawable.ic_more_options),
                onClick = { /*TODO*/ },
                contentDescription = "more options",
                color = color
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun AppBarAction(
    resource: Painter,
    onClick: () -> Unit,
    contentDescription: String,
    color: Color
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = resource,
            contentDescription = contentDescription,
            tint = color)
    }
}