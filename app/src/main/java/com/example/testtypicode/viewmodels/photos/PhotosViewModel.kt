package com.example.testtypicode.viewmodels.photos

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.example.testtypicode.data.pojo.Album
import com.example.testtypicode.data.pojo.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    private val photos = MutableLiveData<List<Photo>>(listOf())
    private var albums = listOf<Album>()
    private lateinit var disposable: Disposable
    private val apiFactory = ApiFactory
    private val apiService = apiFactory.getApiService()

    fun getPhotos() : LiveData<List<Photo>> = photos

    fun loadPhotos(userId: Int) {
        loadAlbumsFromAPI(userId)
    }

    private fun loadAlbumsFromAPI(userId: Int) {
        disposable = apiService.getAlbums(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    albums = result
                },
                { error ->
                    error(error)
                },
                {
                    val result = mutableListOf<Photo>()

                    albums.forEach {
                        loadPhotosFromAPI(it.id)
                    }
                }
            )
    }

    private fun loadPhotosFromAPI(albumId: Int) {
        val list = mutableListOf<Photo>()

        disposable = apiService.getPhotos(albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    list.addAll(result)
                },
                { error ->
                    error(error)
                },
                {
                    photos.value = list
                    photos.value?.forEach {
                        Log.d("PhotosViewModel", "Photo: ${it.albumId} : ${it.title}")
                    }
                }
            )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
                return PhotosViewModel(
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}