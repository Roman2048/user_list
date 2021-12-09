package com.example.mobtest.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mobtest.data.entity.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}