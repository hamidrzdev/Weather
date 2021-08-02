package com.hamiddev.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.hamiddev.weather.BaseFragment
import com.hamiddev.weather.Inflate
import com.hamiddev.weather.R
import com.hamiddev.weather.databinding.MainFragmentBinding
import org.neshan.common.model.LatLng
import timber.log.Timber
import kotlin.coroutines.coroutineContext


class MainFragment :
    BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {
    val viewModel : MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callViewModel()
        observe()
    }

    fun callViewModel() {
        viewModel.getWeather(LatLng(35.83266, 50.99155))
    }

    fun observe() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            Timber.i("wea -> ${it.timezone}")
        }
    }
}