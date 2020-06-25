package com.achesnovitskiy.empoyees.api

import com.example.testtypicode.data.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Observable<List<User>>
}