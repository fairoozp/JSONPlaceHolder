package com.example.jsonplaceholder.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonplaceholder.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

fun <T : Any> callApi(apiRequest: suspend () -> Response<List<T>>): MutableLiveData<List<T>> {
    val liveData = MutableLiveData<List<T>>()
    CoroutineScope(Dispatchers.IO).launch {
        val response = apiRequest.invoke()
        if (response.isSuccessful) {
//            Log.d(MainActivity.TAG, response.body().toString())
            liveData.postValue(response.body())
        } else {
            Log.d(MainActivity.TAG, response.errorBody().toString())
        }
    }
    return liveData
}