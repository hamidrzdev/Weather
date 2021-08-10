package com.hamiddev.weather.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.hamiddev.weather.BaseFragment
import com.hamiddev.weather.BuildConfig
import com.hamiddev.weather.common.dayName
import com.hamiddev.weather.common.fullDate
import com.hamiddev.weather.common.turnGPSOn
import com.hamiddev.weather.common.weatherIconLink
import com.hamiddev.weather.databinding.MainFragmentBinding
import com.hamiddev.weather.model.DayWeather
import com.hamiddev.weather.service.ImageLoadingService
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import org.neshan.common.model.LatLng
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {
    val viewModel: MainFragmentViewModel by viewModels()
    var isGranted = false


    @Inject
    lateinit var imageLoadingService: ImageLoadingService
    private lateinit var locationClient: FusedLocationProviderClient

    override fun initView() {
        super.initView()
        getPermission()
        observe()
        initLocationClient()
    }

    private fun callViewModel(latLng: LatLng) {
        viewModel.getWeather(LatLng(latLng.latitude, latLng.longitude))
    }

    @SuppressLint("MissingPermission")
    fun initLocationClient() {
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (isGranted) {
            turnGPSOn(requireActivity())
            if (checkGps())
                locationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        callViewModel(LatLng(it.latitude,it.longitude))
                    }
                }
            else
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } else
            showSnackBarToGetPermission(requireView())
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

    fun checkGps(): Boolean {
        var gpsStatus = false
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.let {
            gpsStatus = it.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

        return gpsStatus
    }

    private fun getPermission() {
        Dexter.withContext(requireContext())
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        isGranted = it.areAllPermissionsGranted()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }

            }).check()
    }

    private fun showSnackBarToGetPermission(view: View) {
        Snackbar.make(
            view,
            "دسترسی به Location داده نشده است",
            Snackbar.LENGTH_SHORT
        )
            .setAction("دسترسی") {
                startActivity(Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }.show()
    }

}