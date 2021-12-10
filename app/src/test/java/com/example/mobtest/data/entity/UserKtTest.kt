package com.example.mobtest.data.entity

import junit.framework.TestCase
import org.junit.Test

class UserKtTest : TestCase() {

    @Test
    fun `test user with too long first name`() {
        val user = User(
            "1",
            "KodakKodakKodakKodakKodakKodakKodakKodakKodakKodak",
            "Panasonic",
            "kodak@gmail.com",
            "https://kodak.com/1.jpg"
        )
        assert(user.validate().firstName == "Unknown")
    }

    @Test
    fun `test user with too long last name`() {
        val user = User(
            "1",
            "Kodak",
            "PanasonicKodakKodakKodakKodakKodakKodakKodakKodakKodak",
            "kodak@gmail.com",
            "https://kodak.com/1.jpg"
        )
        assert(user.validate().lastName == "Unknown")
    }

    @Test
    fun `test user with too long email`() {
        val user = User(
            "1",
            "Kodak",
            "Panasonic",
            "kodakKodakKodakKodakKodakKodakKodakKodakKodakKodak123@gmail.com",
            "https://kodak.com/1.jpg"
        )
        assert(user.validate().email == "Unknown")
    }

    @Test
    fun `test user with invalid avatar url`() {
        val user = User(
            "1",
            "Kodak",
            "Panasonic",
            "kodak@gmail.com",
            "http://kodak.com/1.jpg"
        )
        assert(user.validate().avatarUrl == "Unknown")
    }
}