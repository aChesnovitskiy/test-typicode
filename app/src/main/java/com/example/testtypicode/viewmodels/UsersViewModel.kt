package com.example.testtypicode.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.achesnovitskiy.empoyees.api.ApiFactory
import com.example.testtypicode.data.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val users = MutableLiveData<List<User>>(listOf())
    private lateinit var disposable: Disposable

    fun getUsers() : LiveData<List<User>> = users

    fun loadUsers() {
        val apiFactory = ApiFactory
        val apiService = apiFactory.getApiService()

        disposable = apiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    users.value = result
                },
                { error ->
                    error(error)
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
            if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
                return UsersViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}