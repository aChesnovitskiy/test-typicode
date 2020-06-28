package com.example.testtypicode.viewmodels.users

import android.app.Application
import androidx.lifecycle.*
import com.example.testtypicode.data.pojo.User
import com.example.testtypicode.repositories.Repository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val users = MutableLiveData<List<User>>(listOf())

    fun getUsers() : LiveData<List<User>> = users

    fun loadUsers() {
        Repository.loadUsersFromApi {
            users.value = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
                return UsersViewModel(
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}