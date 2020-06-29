package com.example.testtypicode.repositories

import android.util.Log
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.example.testtypicode.data.pojo.Album
import com.example.testtypicode.data.pojo.Photo
import com.example.testtypicode.data.pojo.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object Repository {
    private val apiFactory = ApiFactory
    private val apiService = apiFactory.getApiService()
    private var users = mutableListOf<User>()
    private var photos = mutableMapOf<Int, MutableList<Photo>>()
    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable.add(disposable)
    }

    fun loadPhotosFromApi(userId: Int, callback: (List<Photo>) -> Unit) {
        photos[userId] = emptyList<Photo>().toMutableList()
        val disposable = loadAlbumsFromApiObservable(userId)
            .flatMap { albums -> loadPhotosFromApiObservable(albums) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    result.forEach {
                        Log.d("My_Repository", "Album: ${it.albumId}. Photo: ${it.id}")
                    }
                    photos[userId]!!.addAll(result)
                },
                { error ->
                    error.printStackTrace()
                },
                {
                    callback(photos[userId]!!)
                }
            )
        compositeDisposable.add(disposable)
    }

    fun disposeDisposables() {
        compositeDisposable.dispose()
    }

    private fun loadAlbumsFromApiObservable(userId: Int) : Observable<List<Album>> =
        Observable.just(userId)
            .flatMap { id -> apiService.getAlbums(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun loadPhotosFromApiObservable(albumsList: List<Album>) : Observable<List<Photo>> =
        Observable.fromArray(albumsList)
            .flatMapIterable { albums -> albums }
            .flatMap { album -> apiService.getPhotos(album.id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    // TODO dispose combine disposable
}