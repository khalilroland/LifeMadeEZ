package com.example.plannerproject

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request

// KR: Class for fetching user data from online json api using OkHttp
class UsersFetcher(private val ctx: Context) : IUsersFetcher {

    // KR: URL of json Upi
    val UsersURL = "https://my-json-server.typicode.com/mabdel8/json435/users"

    // KR: val client used for making HTTP requests
    // KR: uses caching
    private val client = OkHttpClient.Builder()
        .cache(
            Cache(
                directory = ctx.cacheDir,
                maxSize = 10 * 1024L * 1024L
            )
        )
        .build()

    // KR: function to fetch user ddata from the URL
    //KR: stores them in a list
    override suspend fun fetchUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            // KR: val request builds a http get request for using OkHttp
            val request = Request.Builder()
                .get()
                .url(UsersURL)
                .build()

            // KR: Executing the request
            //KR: stores it in val response
            val response = client.newCall(request).execute()

            // KR: gets response body
            // KR: parsing data into a list of User objects
            val responseBody = response.body
            if (responseBody != null) {
                val jsonString = responseBody.string()
                val gson = Gson()
                val usersArray = gson.fromJson(jsonString, Array<User>::class.java)
                usersArray.toList()
            } else {
                // KR: Returns empty list if response = null
                listOf()
            }
        }
    }
}