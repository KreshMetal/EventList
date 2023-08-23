package com.example.eventslist

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type
import javax.inject.Inject

class SessionsRepository @Inject constructor(val client: OkHttpClient) {

    val URL = "https://gist.githubusercontent.com/AJIEKCX/901e7ae9593e4afd136abe10ca7d510f/raw/61e7c1f037345370cf28b5ae6fdaffdd9e7e18d5/Sessions.json"

    suspend fun getSessions(): List<Session>
    {
        var value = emptyList<Session>()
        val request = Request.Builder()
            .url(URL)
            .get().
            build()

        withContext(Dispatchers.IO)
        {
            client.newCall(request).execute().use {
                if (it.isSuccessful)
                {
                    val gson = Gson()
                    val str = it.body!!.string()
                    Log.d("TAG", str)
                    val itemsListType: Type = object : TypeToken<MutableList<Session?>?>() {}.type

                    value = gson.fromJson(str, itemsListType)
                }
                else Log.d("TAG", "пизда ответ")
            }
        }

        return value
    }

    data class SessionsWrapper(val sessions: List<Session>)
}