package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.BuildConfig
import com.plcoding.cryptotracker.core.domain.data.networking.constructUrl
import com.plcoding.cryptotracker.core.domain.data.networking.safeCall
import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.Result
import com.plcoding.cryptotracker.core.domain.util.map
import com.plcoding.cryptotracker.crypto.data.mapper.toCoin
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class RemoteCoinDataSource
    (private val httpClient: HttpClient) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString = constructUrl("/assets")) {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${BuildConfig.API_KEY}")
                }
            }
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

}