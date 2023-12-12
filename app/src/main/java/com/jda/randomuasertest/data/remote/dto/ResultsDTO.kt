package com.jda.randomuasertest.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jda.randomuasertest.domain.model.RandomUser
import com.jda.randomuasertest.domain.model.UserLocation

data class ResultsDTO(
    @SerializedName("info") val info: InfoDTO,
    @SerializedName("results") val results: List<ResultDTO>
)

fun ResultsDTO.asDomainModel(): List<RandomUser> =
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

fun LocationDTO.toUserLocation() =
    UserLocation(
        latitude = this.coordinates.latitude,
        longitude = this.coordinates.longitude
    )