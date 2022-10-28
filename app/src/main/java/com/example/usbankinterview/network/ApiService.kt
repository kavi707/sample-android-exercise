package com.example.usbankinterview.network

import com.example.usbankinterview.model.CoinData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/ticker/btc-usd")
    suspend fun getBitcoinValue() : Response<CoinData>
}