package com.hamiddev.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamiddev.data.repo.WeatherRepository
import com.hamiddev.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.neshan.common.model.LatLng
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

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
