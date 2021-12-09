package com.example.mobtest

import android.app.Application
import com.example.mobtest.data.MobtestDatabase
import okhttp3.OkHttpClient

class MobtestApplication : Application() {
    val httpClient = OkHttpClient().newBuilder().build()
    val database: MobtestDatabase by lazy { MobtestDatabase.getDatabase(this) }
}