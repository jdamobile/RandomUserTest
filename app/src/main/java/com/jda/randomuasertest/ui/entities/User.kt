package com.jda.randomuasertest.ui.entities

data class User(
    val id: String,
    val displayName: String,
    val email: String,
    val gender: String,
    val dateJoined: String,
    val phone: String,
    val latitude: String,
    val longitude: String,
    val photo: String,
)

fun getUsers(): List<User> {
    return (1..10).map {
        User(
            id = it.toString(),
            displayName = "Name $it",
            email = "name$it@xxx.com",
            gender = if (it % 2 == 0) "female"
            else "male",
            dateJoined = "21/08/2022",
            phone = "=34 5555555555",
            latitude = "000000$it",
            longitude = "000000$it",
            photo = "https://loremflickr.com/400/400/cat"
        )
    }
}