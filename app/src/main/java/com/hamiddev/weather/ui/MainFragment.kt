package com.hamiddev.weather.ui

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamiddev.weather.BaseFragment
import com.hamiddev.weather.common.dayName
import com.hamiddev.weather.common.fullDate
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.MainFragmentBinding
import com.hamiddev.weather.model.DayWeather
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
                it.degreeTv.text = weather.current.feels_like.toInt().toString()
                it.cTv.text = "°C"
                it.cityNameTv.text = weather.timezone
                it.weatherInfoTv.text = weather.current.weather[0].description
                it.weather7daysTv.text = "آب هوای 7 روز آینده"

                it.hourForecast.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                it.hourForecast.adapter = HourWeatherAdapter(imageLoadingService).apply {
                    hourWeatherList =
                        weather.hourly.subList(0, 25).map {
                            it.toHourWeather()
                        }.toMutableList()
                }

                val onItemClicked = object : DayWeatherListener {
                    override fun onItemClicked(dayModel: DayWeather) {
                        val itemClicked = weather.daily.filter {
                            it.dt == dayModel.timestamp
                        }.map {
                            it.toDetailWeather()
                        }

                        Navigation.findNavController(requireView()).navigate(
                            MainFragmentDirections.actionMainFragmentToDetailWeatherFragment(
                                itemClicked[0]
                            )
                        )

                    }
                }

                it.dayForecast.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                it.dayForecast.adapter =
                    DayWeatherAdapter(imageLoadingService, onItemClicked).apply {
                        dayWeatherList =
                            weather.daily.map {
                                it.toDayWeather()
                            }.toMutableList()
                    }
            }
        }
    }

}