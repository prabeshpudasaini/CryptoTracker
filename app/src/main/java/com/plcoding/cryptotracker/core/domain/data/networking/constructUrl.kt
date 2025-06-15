package com.plcoding.cryptotracker.core.domain.data.networking

import com.plcoding.cryptotracker.BuildConfig


//fun constructUrl(url: String): String {
//    return when {
//        url.contains(BuildConfig.BASE_URL) -> url
//        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
//        else -> BuildConfig.BASE_URL + url
//
//    }
//
//}

fun constructUrl(path: String, apiKey: String = BuildConfig.API_KEY): String {
    val base = if (path.startsWith("/")) BuildConfig.BASE_URL + path.drop(1)
    else BuildConfig.BASE_URL + path
    return "$base?apiKey=$apiKey"
}