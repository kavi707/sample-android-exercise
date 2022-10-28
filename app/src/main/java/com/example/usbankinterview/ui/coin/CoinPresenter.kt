package com.example.usbankinterview.ui.coin

import com.example.usbankinterview.network.ApiManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinPresenter(private val view: CoinContract.View): CoinContract.Presenter {

    override fun fetchBitcoinData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiManger.getApiService()?.getBitcoinValue()
            if (response?.code() == 200)
                view.setDataToText(response.body()?.ticker?.price)
            else
                view.setDataToText("Error!")
        }
    }
}