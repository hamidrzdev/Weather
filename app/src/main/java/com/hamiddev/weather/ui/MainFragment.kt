package com.hamiddev.weather.ui

import androidx.fragment.app.viewModels
import com.hamiddev.weather.BaseFragment
import com.hamiddev.weather.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.neshan.common.model.LatLng
import timber.log.Timber

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {
    val viewModel: MainFragmentViewModel by viewModels()

    override fun initView() {
        super.initView()
        callViewModel()
        observe()
    }

    private fun callViewModel() {
        viewModel.getWeather(LatLng(35.83266, 50.99155))
    }

    private fun observe() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            Timber.i("weaaa -> ${it.timezone}")
        }
    }
}