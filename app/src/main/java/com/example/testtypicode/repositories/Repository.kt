package com.example.testtypicode.repositories

import android.util.Log
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.example.testtypicode.data.pojo.Album
import com.example.testtypicode.data.pojo.Photo
import com.example.testtypicode.data.pojo.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Repository {
    private val apiFactory = ApiFactory
    private val apiService = apiFactory.getApiService()
    private var users = mutableListOf<User>()
    private var albums = mutableListOf<Album>()
    private var photos = mutableListOf<Photo>()

    fun loadUsersFromApi(callback: (List<User>) -> Unit) {
        val disposable = apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    users.addAll(result)
                },
                { error ->
                    error.printStackTrace()
                },
                {
                    callback(users)
                }
            )
    }

    fun loadAlbumsFromApi(userId: Int, callback: (List<User>) -> Unit) {
        val disposable = apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    users.addAll(result)
                },
                { error ->
                    error.printStackTrace()
                },
                {
                    callback(users)
                }
            )
    }

    // TODO
//    fun loadAlbumsAndPhotosFromApi(userId: Int, callback: (List<Photo>) -> Unit) {
//        val disposable = apiService.getAlbums(userId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    albums.addAll(result)
//                },
//                { error ->
//                    error(error)
//                },
//                {
//                    Observable.fromIterable(albums)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                            { album ->
//                                apiService.getPhotos(album.id)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(
//                                        { result ->
//                                            photos.addAll(result)
//                                        },
//                                        { error ->
//                                            error.printStackTrace()
//                                        }
//                                    )
//                            },
//                            { error ->
//                                error(error)
//                            },
//                            {
//                                callback(photos)
//                            }
//                        )
//                }
//            )
//    }
//
//    private fun loadPhotosFromApi(albumId: Int) {
//        val disposable = apiService.getPhotos(albumId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    result.forEach {
//                        Log.d("Repository", "Photo: ${it.albumId} : ${it.id}")
//                    }
//                    photos.addAll(result)
//                },
//                { error ->
//                    error.printStackTrace()
//                }
//            )
//    }

    // TODO dispose combine disposable
}