package com.hamiddev.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamiddev.data.repo.WeatherRepository
import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.neshan.common.model.LatLng

class MainFragmentViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResponse>()

    fun getWeather(latLng: LatLng) {
        viewModelScope.launch {
            weatherRepository.getWeather(latLng, "ce868ee6aa3ec9850c40345ef8d85145")
                .collect {
                    weatherLiveData.value = it
                }
        }
    }
}