package com.jda.randomuasertest.data.network.model

import com.jda.randomuasertest.domain.model.RandomUser
import com.jda.randomuasertest.domain.model.UserLocation

data class Results(
    val info: Info,
    val results: List<Result>
)

fun Results.asDomainModel(): List<RandomUser> =
    results.map {
        RandomUser(
            id = it.id.name,
            displayName = "${it.name.first} ${it.name.last}",
            email = it.email,
            gender = it.gender,
            birthday = it.dob.date.toDateFormat(),
            phone = it.phone,
            location = it.location.toUserLocation(),
            photo = it.picture.large
        )
    }

private fun String.toDateFormat() =
    this.substringBefore(
        delimiter = "T",
        missingDelimiterValue = "T not found"
    ).replace("-", "/")

fun Location.toUserLocation() =
    UserLocation(
        latitude = this.coordinates.latitude,
        longitude = this.coordinates.longitude
    )