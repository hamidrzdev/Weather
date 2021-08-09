package com.hamiddev.weather.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamiddev.weather.common.dayName
import com.hamiddev.weather.common.formatTemp
import com.hamiddev.weather.common.fullDate
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.ItemDayWeatherBinding
import com.hamiddev.weather.databinding.ItemHourTimeBinding
import com.hamiddev.weather.model.DayWeather
import com.hamiddev.weather.model.HourWeather
import com.hamiddev.weather.service.ImageLoadingService
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat

class DayWeatherAdapter(val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<DayWeatherAdapter.DayWeatherViewHolder>() {
    var dayWeatherList = mutableListOf<DayWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class DayWeatherViewHolder(binding: ItemDayWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val fullDate = binding.fullDateTv
        private val dayName = binding.dayNameTv
        private val temp = binding.temps
        private val image = binding.image

        @SuppressLint("SetTextI18n")
        fun bind(dayModel: DayWeather) {
            fullDate.text = fullDate(dayModel.timestamp)
            dayName.text = dayName(dayModel.timestamp)
            imageLoadingService.load(image, weatherIconLink(dayModel.image))
            temp.text = "${formatTemp(dayModel.minTemp)} / ${formatTemp(dayModel.maxTemp)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherViewHolder =
        DayWeatherViewHolder(
            ItemDayWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DayWeatherViewHolder, position: Int) {
        holder.bind(dayWeatherList[position])
    }

    override fun getItemCount(): Int = dayWeatherList.size
}

