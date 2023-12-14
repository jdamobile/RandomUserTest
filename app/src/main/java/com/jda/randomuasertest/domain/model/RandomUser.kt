package com.jda.randomuasertest.domain.model

import com.jda.randomuasertest.ui.entities.User

data class RandomUser(
    val id: String,
    val displayName: String,
    val email: String,
    val gender: String,
    val dateJoined: String,
    val phone: String,
    val location: UserLocation,
    val photo: String,
    )

fun RandomUser.toSimpleUser(): User {
    return User(
        id = this.id,
        displayName = this.displayName,
        email = this.email,
        gender = this.gender,
        dateJoined = this.dateJoined,
        phone = this.phone,
        latitude = this.location.latitude,
        longitude = this.location.longitude,
        photo = this.photo
    )
}