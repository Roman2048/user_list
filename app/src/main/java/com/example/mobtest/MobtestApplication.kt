package com.example.mobtest

import android.app.Application
import com.example.mobtest.data.MobtestDatabase

class MobtestApplication : Application() {
    val database: MobtestDatabase by lazy { MobtestDatabase.getDatabase(this) }
}
