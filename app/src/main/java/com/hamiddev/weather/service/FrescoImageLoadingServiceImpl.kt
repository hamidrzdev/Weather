package com.hamiddev.weather.service

import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView

class FrescoImageLoadingServiceImpl : ImageLoadingService {
    override fun load(imageView: SimpleDraweeView, url: String) {
        imageView.setImageURI(url)
    }
}