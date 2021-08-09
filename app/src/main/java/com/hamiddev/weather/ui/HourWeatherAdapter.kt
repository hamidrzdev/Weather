package com.hamiddev.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.ItemHourTimeBinding
import com.hamiddev.weather.model.HourWeather
import com.hamiddev.weather.service.ImageLoadingService
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat

class HourWeatherAdapter(val imageLoadingService: ImageLoadingService) : RecyclerView.Adapter<HourWeatherAdapter.HourWeatherViewHolder>() {
    var hourWeatherList = mutableListOf<HourWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class HourWeatherViewHolder(val binding: ItemHourTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val hour = binding.hourTv
        val temp = binding.degreeTv
        val image = binding.weatherImg
        fun bind(hourModel: HourWeather) {
            hour.text = digitHour(hourModel.hour)
            temp.text = hourModel.temp.toString()
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


    private fun digitHour(timestamp: Long): String {
        val persianDate = PersianDate(timestamp * 1000)
        val persianDateFormat = PersianDateFormat("H")
        val formattedDate = persianDateFormat.format(persianDate)
        return "$formattedDate:00"
    }

}

