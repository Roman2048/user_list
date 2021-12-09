package com.example.mobtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mobtest.MobtestApplication
import com.example.mobtest.data.dao.UserDao
import com.example.mobtest.data.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {
    val users = userDao.getAll()
    fun insert(user: User) = CoroutineScope(Dispatchers.IO).launch {
        userDao.insert(user)
    }
}