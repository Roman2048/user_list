package com.example.mobtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey  @Json(name = "id") var id: String,
    @Json(name = "first_name") var firstName: String? = null,
    @Json(name = "last_name") var lastName: String? = null,
    @Json(name = "email") var email: String? = null,
    @Json(name = "avatar") var avatarUrl: String? = null,
) : Serializable
