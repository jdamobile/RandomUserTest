package com.jda.randomuasertest.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
fun String.toEncodedString(): String = Base64.getUrlEncoder().encodeToString(this.toByteArray())

@RequiresApi(Build.VERSION_CODES.O)
fun String.toDecodedString() = String(Base64.getUrlDecoder().decode(this))
