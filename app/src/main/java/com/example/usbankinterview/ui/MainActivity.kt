package com.example.usbankinterview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usbankinterview.R
import com.example.usbankinterview.network.ApiManger
import com.example.usbankinterview.ui.coin.CoinFragment

class MainActivity : AppCompatActivity() {

    private val bitCoinFragment: CoinFragment = CoinFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiManger.init(this)

        supportFragmentManager.beginTransaction().replace(R.id.container, bitCoinFragment, "BITC").commit()
    }
}