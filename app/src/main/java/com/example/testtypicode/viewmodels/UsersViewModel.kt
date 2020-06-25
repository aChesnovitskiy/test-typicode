package com.example.testtypicode.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtypicode.data.User
import com.example.testtypicode.network.JSONUtils
import com.example.testtypicode.network.NetworkUtils


class UsersViewModel : ViewModel() {
    private val users = MutableLiveData<List<User>>(listOf())
    private val networkUtils = NetworkUtils
    private val jsonUtils = JSONUtils

    fun getUsers() : LiveData<List<User>> {
        val usersFromAPI = downloadUsersFromAPI()

        users.value = usersFromAPI

        return users
    }

    private fun downloadUsersFromAPI(): List<User> {
        val usersJSON = networkUtils.getUsersJSONFromNetwork()

        return jsonUtils.getUsersFromJSON(usersJSON)
    }
}