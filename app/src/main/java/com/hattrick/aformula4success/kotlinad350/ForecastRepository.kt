package com.hattrick.aformula4success.kotlinad350

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForcast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForcast

    fun loadForecast(zipcode: String) {
        val randomValues = List(10) {
            Random.nextFloat().rem(100) * 100
        }
        val  forecastItems =  randomValues.map {temp ->
            DailyForecast (temp,getTempDescription(temp))
        }
        _weeklyForcast.setValue(forecastItems)
    }
    private  fun getTempDescription (temp : Float) : String {
        return when (temp){
            in Float.MIN_VALUE.rangeTo( 0F) -> "Anything"
            in 0F.rangeTo( 32F) -> "too cold"
            in 0F.rangeTo( 55F) -> "cold"
            in 0F.rangeTo( 65F) -> "brisk"
            in 0F.rangeTo( 80F) -> "nice"
            in 0F.rangeTo( 98F) -> "Hot"
            in 0F.rangeTo( 100F) -> "too hot"
            else -> "does not compute"

        }

    }
}