package com.hamiddev.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.ItemHourTimeBinding
import com.hamiddev.weather.model.HourWeather
import com.hamiddev.weather.service.ImageLoadingService
import javax.inject.Inject

class HourWeatherAdapter : RecyclerView.Adapter<HourWeatherAdapter.HourWeatherViewHolder>() {
    val hourWeatherList = mutableListOf<HourWeather>()

    @Inject
    lateinit var imageLoadingService: ImageLoadingService

    inner class HourWeatherViewHolder(val binding: ItemHourTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val hour = binding.hourTv
        val temp = binding.degreeTv
        val image = binding.weatherImg
        fun bind(hourModel: HourWeather) {
            hour.text = hourModel.hour
            temp.text = hourModel.temp
            imageLoadingService.load(image, weatherIconLink(hourModel.image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourWeatherViewHolder =
        HourWeatherViewHolder(
            ItemHourTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HourWeatherViewHolder, position: Int) {
        holder.bind(hourWeatherList[position])
    }

    override fun getItemCount(): Int = hourWeatherList.size
}