package com.example.testtypicode.network

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutionException

object NetworkUtils {
    private const val BASE_URL_USERS = "https://jsonplaceholder.typicode.com/users"

    private fun buildUsersURL(): URL = URL(BASE_URL_USERS)

    fun getUsersJSONFromNetwork(): JSONObject? {
        var result: JSONObject? = null
        val url: URL = buildUsersURL()
        try {
            result = JSONLoadTask().execute(url).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return result
    }

    private class JSONLoadTask : AsyncTask<URL?, Void?, JSONObject?>() {
        override fun doInBackground(vararg urls: URL?): JSONObject? {
            var result: JSONObject? = null
            if (urls[0] == null || urls.isEmpty()) {
                return result
            }
            var connection: HttpURLConnection? = null
            try {
                connection = urls[0]?.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                val inputStreamReader =
                    InputStreamReader(inputStream)
                val reader = BufferedReader(inputStreamReader)
                val builder = StringBuilder()
                var line = reader.readLine()
                while (line != null) {
                    builder.append(line)
                    line = reader.readLine()
                }
                result = JSONObject(builder.toString())
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            Log.d("My_", result.toString())
            return result
        }
    }
}