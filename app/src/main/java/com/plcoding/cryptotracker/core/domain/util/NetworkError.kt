package com.plcoding.cryptotracker.core.domain.util

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUEST,
    NOT_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}