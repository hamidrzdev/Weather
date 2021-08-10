package com.hamiddev.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamiddev.weather.common.handleException
import com.hamiddev.weather.data.repo.WeatherRepository
import com.hamiddev.weather.model.server.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.neshan.common.model.LatLng
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    val weatherLiveData = MutableLiveData<WeatherResponse>()
    val showProgressBar = MutableLiveData<Boolean>()
    val latLng = MutableLiveData<LatLng>()

    fun getWeather(latLng: LatLng) {
        viewModelScope.launch {
            showProgressBar.value = true
            weatherRepository.getWeather(latLng)
                .handleException()
                .collect {
                    weatherLiveData.value = it
                }
            showProgressBar.value = false
        }
    }
}
