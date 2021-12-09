package com.example.mobtest.data.entity

import com.squareup.moshi.Json

data class Resp(
    @Json(name = "data") val users: List<User>
)
