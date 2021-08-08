package com.hamiddev.weather.service

import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoadingService {
    fun load(imageView: SimpleDraweeView, url:String)
}