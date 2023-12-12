package com.jda.randomuasertest.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.jda.randomuasertest.R
import com.jda.randomuasertest.ui.entities.User

@Composable
fun UserItem(onClick: () -> Unit, user: User) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 16.dp)
            .clickable(onClick = onClick)
    ) {
        val (avatar, userInfo, footer, endIcon) = createRefs()
        val barrier = createEndBarrier(avatar)

        Avatar(modifier = Modifier.constrainAs(avatar) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        },
            image = user.photo)

        UserInfo(modifier = Modifier.constrainAs(userInfo) {
            start.linkTo(barrier)
            end.linkTo(endIcon.start, margin = 17.dp)
            top.linkTo(avatar.top)
            width = Dimension.fillToConstraints
        },
            user = user)

        IconNavigator(modifier = Modifier.constrainAs(endIcon) {
            end.linkTo(parent.end, margin = 8.dp)
            top.linkTo(avatar.top)
            bottom.linkTo(avatar.bottom)
        })

        ItemDecorator(modifier = Modifier.constrainAs(footer) {
            start.linkTo(barrier)
            end.linkTo(parent.end)
            top.linkTo(avatar.bottom, margin = 16.dp)
            width = Dimension.fillToConstraints
        })
    }
}

@Composable
fun IconNavigator(modifier: Modifier, onIconSelected: () -> Unit = { }) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_small),
        contentDescription = "go to details",
        tint = Color.LightGray,
        modifier = modifier.clickable { onIconSelected }
    )
}

@Composable
fun UserInfo(modifier: Modifier, user: User) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Text(
            text = user.displayName,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
        SpaceVertically(height = 8.dp)
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color(0xFF8E8E93),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun Avatar(modifier: Modifier, image: String) {
    AsyncImage(
        model = image,
        contentDescription = "User Avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(52.dp)
            .clip(CircleShape)
            .border(1.dp, Color.LightGray, CircleShape),
        placeholder = painterResource(id = R.drawable.no_image)
    )
}

@Composable
fun SpaceVertically(height: Dp) {
    Spacer(modifier = Modifier.size(height))
}

@Composable
fun ItemDecorator(modifier: Modifier, padding: Dp = 16.dp) {
    Divider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .padding(start = padding),
        color = Color(0xFFE7E7E7)
    )
}