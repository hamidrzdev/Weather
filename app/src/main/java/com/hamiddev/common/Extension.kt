package com.hamiddev.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import timber.log.Timber

fun <T> Flow<T>.handleException():Flow<T> =
    catch { e -> Timber.i("ssdss -> ${e.message}") }
