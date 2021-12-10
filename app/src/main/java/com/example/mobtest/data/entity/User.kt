package com.example.mobtest.data.entity

import android.os.Parcelable
import android.util.Log
import android.webkit.URLUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey @Json(name = "id") var id: String,
    @Json(name = "first_name") var firstName: String? = null,
    @Json(name = "last_name") var lastName: String? = null,
    @Json(name = "email") var email: String? = null,
    @Json(name = "avatar") var avatarUrl: String? = null,
) : Parcelable

fun User.validate(): User {
    return this.apply {
        if (id.toIntOrNull() == null) {
            Log.e("user_validation", "Id '$id' is not valid")
            return@apply
        }
        if (firstName != null && (firstName!!.length > 30 || firstName.isNullOrBlank())) {
            firstName = "Unknown"
        }
        if (lastName != null && (lastName!!.length > 30 || lastName.isNullOrBlank())) {
            lastName = "Unknown"
        }
        if (email != null && (email!!.length > 50 || email.isNullOrBlank())) {
            email = "Unknown"
        }
        if (avatarUrl != null && !avatarUrl!!.startsWith("https://")) {
            avatarUrl = "Unknown"
        }
    }
}
