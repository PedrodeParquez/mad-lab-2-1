package com.example.mad_lab_2_1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad_lab_2_1.data.RetrofitService
import com.example.mad_lab_2_1.data.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherData>()

    fun retrofitAPI(city : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val weatherRetrofit = RetrofitService.create().getWeatherForecast(city, "e95a93c258eb63a193e07e680c12daab", "metric", "ru")
                withContext(Dispatchers.Main) {
                    weatherData.postValue(weatherRetrofit)
                }
            } catch (ex : Exception) { }
        }
    }
}