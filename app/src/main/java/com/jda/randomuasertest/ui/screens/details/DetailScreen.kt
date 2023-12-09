package com.jda.randomuasertest.ui.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jda.randomuasertest.R
import com.jda.randomuasertest.data.toDecodedString
import com.jda.randomuasertest.ui.screens.main.ItemDecorator
import com.jda.randomuasertest.ui.screens.main.MainAppBar
import com.jda.randomuasertest.ui.screens.main.SpaceVertically

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    onBackPressed: () -> Unit,
    photo: String,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        UserProfile(
            photo = photo,
            name = name,
            email = email,
            gender = gender,
            date = date,
            phone = phone,
            latitude = latitude,
            longitude = longitude
        )
        MainAppBar(color = Color.White, title = name, onBackPressed = onBackPressed)
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfile(
    photo: String,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (header, avatar, edit, content) = createRefs()
        DetailHeader(modifier = Modifier.constrainAs(header) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })
        ProfileAvatar(
            modifier = Modifier.constrainAs(avatar) {
                start.linkTo(parent.start, margin = 17.dp)
                top.linkTo(header.bottom)
                bottom.linkTo(header.bottom)
            },
            photo = photo
        )
        EditActions(modifier = Modifier.constrainAs(edit) {
            top.linkTo(header.bottom)
            end.linkTo(parent.end)
        })
        Content(
            modifier = Modifier.constrainAs(content) {
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(avatar.bottom, margin = 12.dp)
                width = Dimension.fillToConstraints
            },
            name = name,
            email = email,
            gender = gender,
            date = date,
            phone = phone,
            latitude = latitude,
            longitude = longitude
        )


    }
}

@Composable
fun Address(
    modifier: Modifier,
    latitude: String,
    longitude: String
) {
    Column(
        modifier = modifier
            .padding(start = 52.dp, top = 5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Dirección",
            fontSize = 11.sp,
            color = Color(0xFF707070),
            fontWeight = FontWeight.Normal
        )
        SpaceVertically(height = 11.dp)
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.default_map),
            contentScale = ContentScale.Crop,
            contentDescription = "user address"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    modifier: Modifier,
    name: String,
    email: String,
    gender: String,
    date: String,
    phone: String,
    latitude: String,
    longitude: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InfoItem(
            modifier = Modifier,
            title = "Nombre y Apellidos",
            description = name,
            icon = painterResource(id = R.drawable.ic_profile_user)
        )

        InfoItem(
            modifier = Modifier,
            title = "Email",
            description = email,
            icon = painterResource(id = R.drawable.ic_mailbox)
        )

        InfoItem(
            modifier = Modifier,
            title = "Género",
            description = gender,
            icon = painterResource(id = R.drawable.ic_gender)
        )

        InfoItem(
            modifier = Modifier,
            title = "Fecha de registro",
            description = date.toDecodedString(),
            icon = painterResource(id = R.drawable.ic_calendar)
        )

        InfoItem(
            modifier = Modifier,
            title = "Telefono",
            description = phone,
            icon = painterResource(id = R.drawable.ic_phone)
        )

        Address(
            modifier = Modifier,
            latitude = latitude,
            longitude = longitude
        )
    }
}

@Composable
fun InfoItem(modifier: Modifier, title: String, description: String, icon: Painter) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (startIcon, userName, itemDecorator) = createRefs()
        val barrier = createEndBarrier(startIcon)

        StartIcon(
            icon = icon,
            contentDescription = "Profile",
            modifier = Modifier.constrainAs(startIcon) {
                start.linkTo(parent.start)
                top.linkTo(userName.top)
                bottom.linkTo(itemDecorator.bottom)
            })
        InfoDetail(
            modifier = Modifier.constrainAs(userName) {
                start.linkTo(barrier)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            title = title,
            description = description
        )
        ItemDecorator(
            modifier = Modifier.constrainAs(itemDecorator) {
                start.linkTo(barrier)
                end.linkTo(parent.end)
                top.linkTo(userName.bottom, margin = 8.dp)
                width = Dimension.fillToConstraints
            },
            padding = 20.dp
        )
    }
}

@Composable
private fun InfoDetail(modifier: Modifier, title: String, description: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 11.sp,
            color = Color(0xFF707070),
            fontWeight = FontWeight.Normal
        )
        SpaceVertically(height = 8.dp)

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun StartIcon(icon: Painter, contentDescription: String, modifier: Modifier) {
    Icon(painter = icon, contentDescription = contentDescription, modifier = modifier)
}

@Composable
fun EditActions(modifier: Modifier) {
    Row(modifier = modifier) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "camera",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit",
                tint = Color.Black
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileAvatar(modifier: Modifier, photo: String) {
    AsyncImage(
        model = photo.toDecodedString(),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(77.dp)
            .clip(CircleShape)
            .border(3.dp, Color.White, CircleShape)
            .background(Color.LightGray),
        placeholder = painterResource(id = R.drawable.no_image)
    )
}

@Composable
fun DetailHeader(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile_bg),
        contentDescription = "header",
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        contentScale = ContentScale.FillBounds
    )

}
