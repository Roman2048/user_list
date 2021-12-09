package com.example.mobtest.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mobtest.MobtestApplication
import com.example.mobtest.data.dao.UserDao
import com.example.mobtest.data.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MobtestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: MobtestDatabase? = null
        fun getDatabase(context: Context): MobtestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, MobtestDatabase::class.java, "mobtest").build()
                INSTANCE = instance
                instance
            }
        }
    }

}
