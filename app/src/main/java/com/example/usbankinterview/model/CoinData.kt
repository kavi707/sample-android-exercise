package com.example.usbankinterview.model

data class CoinData(
    val timestamp: Long,
    val success: Boolean,
    val error: String,
    val ticker: Ticker
)

data class Ticker(
    val base: String,
    val target: String,
    val price: String,
    val volume: String,
    val change: String
)