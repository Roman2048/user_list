package com.example.mobtest.network

import retrofit2.http.GET

interface UserApiService {
    @GET("api/users?per_page=100")
    suspend fun getUser(): UsersResponse
}

