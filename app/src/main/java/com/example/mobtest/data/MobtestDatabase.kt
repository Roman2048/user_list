package com.example.mobtest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobtest.data.dao.UserDao
import com.example.mobtest.data.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MobtestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
