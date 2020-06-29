package com.example.testtypicode.viewmodels.photos

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.testtypicode.data.pojo.Photo
import com.example.testtypicode.repositories.Repository

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    private val photos = MutableLiveData<List<Photo>>(listOf())

    fun getPhotos() : LiveData<List<Photo>> = photos

    fun loadPhotos(userId: Int) {
        Repository.loadPhotosFromApi(userId) { photosFromApi ->
            photos.value = photosFromApi.sortedBy { it.id }
            Log.d("My_PhotosViewModel", "Photos size: ${photos.value!!.size}")
        }
    }

    fun disposeDisposables() {
        Repository.disposeDisposables()
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