package com.jda.randomuasertest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class IdDTO(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String?
)
