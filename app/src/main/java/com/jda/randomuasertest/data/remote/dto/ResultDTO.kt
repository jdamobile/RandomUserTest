package com.jda.randomuasertest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResultDTO(
    @SerializedName("cell") val cell: String,
    @SerializedName("dob") val dob: DobDTO,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("id") val id: IdDTO,
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("login") val login: LoginDTO,
    @SerializedName("name") val name: NameDTO,
    @SerializedName("nat") val nat: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: PictureDTO,
    @SerializedName("registered") val registered: RegisteredDTO
)
