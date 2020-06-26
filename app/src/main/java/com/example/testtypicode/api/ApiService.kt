package com.achesnovitskiy.empoyees.api

import com.example.testtypicode.data.pojo.Album
import com.example.testtypicode.data.pojo.Photo
import com.example.testtypicode.data.pojo.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("albums")
    fun getAlbums(@Query("userId") userId: Int): Observable<List<Album>>

    @GET("photos")
    fun getPhotos(@Query("albumId") albumId: Int): Observable<List<Photo>>
}