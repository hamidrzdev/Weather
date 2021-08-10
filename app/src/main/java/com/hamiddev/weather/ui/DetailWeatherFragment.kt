package com.hamiddev.weather.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hamiddev.weather.R
import com.hamiddev.weather.common.dayName
import com.hamiddev.weather.common.digitalDate
import com.hamiddev.weather.common.formatTemp
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.FragmentWeatherDetailBinding
import com.hamiddev.weather.service.ImageLoadingService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailWeatherFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentWeatherDetailBinding

    @Inject
    lateinit var imageLoadingService: ImageLoadingService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog!!.setOnShowListener {
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val bundle = DetailWeatherFragmentArgs.fromBundle(requireArguments()).weather

        binding.dayNameTv.text = dayName(bundle.timestamp)
        imageLoadingService.load(binding.image, weatherIconLink(bundle.image))
        binding.minTempTv.text = formatTemp( bundle.minTemp.toString())
        binding.maxTempTv.text = formatTemp(bundle.maxTemp.toString())
        binding.toloValue.text = digitalDate(bundle.sunrise.toLong())
        binding.qoroobValue.text = digitalDate(bundle.sunset.toLong())
        binding.windSpeedValue.text = bundle.windSpeed.toString()
        binding.uvValue.text = bundle.uv.toString()
        binding.rainValue.text = "${bundle.rain * 100 }%"
    }
}