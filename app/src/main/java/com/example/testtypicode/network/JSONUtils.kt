package com.example.testtypicode.network

import com.example.testtypicode.data.User
import org.json.JSONObject

object JSONUtils {
    private const val KEY_RESULTS = "results"
    private const val KEY_USER_ID = "id"
    private const val KEY_USER_NAME = "name"

    fun getUsersFromJSON (jsonObject: JSONObject?) : List<User> {
        val result = mutableListOf<User>()

        jsonObject ?: return result

        val jsonArray = jsonObject.getJSONArray(KEY_RESULTS)

        for (i in 0..jsonArray.length()) {
            val objectUser = jsonArray.getJSONObject(i)

            val id = objectUser.getInt(KEY_USER_ID)
            val name = objectUser.getString(KEY_USER_NAME)

            val user = User(id, name)

            result.add(user)
        }

        return result
    }
}