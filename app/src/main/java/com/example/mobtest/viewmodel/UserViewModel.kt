package com.example.mobtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobtest.data.dao.UserDao
import com.example.mobtest.data.entity.User
import com.example.mobtest.data.entity.validate
import com.example.mobtest.network.UserApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    var users = userDao.getAll()

    var currentUser: User? = null

    private val handler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.localizedMessage)
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO + handler) {
        userDao.insert(user)
    }

    fun delete(user: User) = viewModelScope.launch(Dispatchers.IO + handler) {
        userDao.delete(user)
    }

    fun loadUsersFromNetwork() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            UserApi.retrofitService.getUser().users.forEach { userDao.insert(it.validate()) }
        }
    }

    init {
        loadUsersFromNetwork()
    }
}