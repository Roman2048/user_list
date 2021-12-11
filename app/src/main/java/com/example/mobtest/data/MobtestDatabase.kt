package com.example.mobtest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobtest.data.dao.UserDao
import com.example.mobtest.data.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MobtestDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: MobtestDatabase? = null
        fun getDatabase(context: Context): MobtestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, MobtestDatabase::class.java, "mobtest").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
