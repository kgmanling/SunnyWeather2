package com.sunnyweather.android.logic.network

import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val place = placeResponse.places
                Result.success(place)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: java.lang.Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}