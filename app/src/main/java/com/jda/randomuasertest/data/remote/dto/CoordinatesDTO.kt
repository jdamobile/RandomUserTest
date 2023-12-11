package com.jda.randomuasertest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoordinatesDTO(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)
