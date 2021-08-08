package com.hamiddev.weather.ui

import androidx.fragment.app.viewModels
import com.hamiddev.weather.BaseFragment
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.MainFragmentBinding
import com.hamiddev.weather.service.ImageLoadingService
import dagger.hilt.android.AndroidEntryPoint
import org.neshan.common.model.LatLng
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {
    val viewModel: MainFragmentViewModel by viewModels()
    val persianDateFormat = PersianDateFormat("l")

    @Inject
    lateinit var imageLoadingService: ImageLoadingService

    override fun initView() {
        super.initView()
        callViewModel()
        observe()
    }

    private fun callViewModel() {
        viewModel.getWeather(LatLng(35.715298, 51.404343))
    }

    private fun observe() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weather ->
            val timestamp = weather.current.dt.toLong()
            binding?.let {
                imageLoadingService.load(
                    it.imageView,
                    weatherIconLink(weather.current.weather[0].icon)
                )
                it.dayNameTv.text = dayName(timestamp)
                it.dateTv.text = fullDate(timestamp)
                it.degreeTv.text = weather.current.temp.toInt().toString()
                it.cTv.text = "°C"
                it.cityNameTv.text = weather.timezone
                it.weatherInfoTv.text = weather.current.weather[0].description
            }
        }
    }

    private fun dayName(timestamp: Long): String {
        val persianDate = PersianDate(timestamp * 1000)
        val persianDateFormat = PersianDateFormat("l")

        if (persianDate.shDay == PersianDate().shDay)
            return "امروز"

        return persianDateFormat.format(persianDate)
    }

    private fun fullDate(timestamp: Long): String {
        val persianDate = PersianDate(timestamp * 1000)
        val persianDateFormat = PersianDateFormat("d F Y")
        return persianDateFormat.format(persianDate)
    }
}