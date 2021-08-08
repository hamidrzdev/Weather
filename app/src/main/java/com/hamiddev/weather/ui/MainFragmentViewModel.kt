package com.hamiddev.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamiddev.weather.common.handleException
import com.hamiddev.weather.data.repo.WeatherRepository
import com.hamiddev.weather.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.neshan.common.model.LatLng
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResponse>()

    fun getWeather(latLng: LatLng) {
        viewModelScope.launch {
            weatherRepository.getWeather(latLng)
                .handleException()
                .collect {
                    weatherLiveData.value = it
                }
        }
    }
}
