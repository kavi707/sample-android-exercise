package com.example.usbankinterview.ui.coin

interface CoinContract {

    interface View{
        fun setDataToText(value: String?)
    }

    interface Presenter {
        fun fetchBitcoinData()
    }
}