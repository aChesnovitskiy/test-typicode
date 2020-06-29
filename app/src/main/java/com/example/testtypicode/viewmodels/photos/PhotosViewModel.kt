package com.example.testtypicode.viewmodels.photos

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.example.testtypicode.data.pojo.Album
import com.example.testtypicode.data.pojo.Photo
import com.example.testtypicode.repositories.Repository

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    private val photos = MutableLiveData<List<Photo>>(listOf())

    fun getPhotos() : LiveData<List<Photo>> = photos

    fun loadPhotos(userId: Int) {
        Repository.loadAlbumsAndPhotosFromApi(userId) { photosFromApi ->
            photos.value = photosFromApi.sortedBy { it.id }
            Log.d("My_PhotosViewModel", "Photos size: ${photos.value!!.size}")
        }
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