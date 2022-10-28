package com.example.usbankinterview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usbankinterview.R
import com.example.usbankinterview.ui.coin.BitCoinValueFragment

class MainActivity : AppCompatActivity() {

    private val bitCoinFragment: BitCoinValueFragment = BitCoinValueFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, bitCoinFragment, "BITC").commit()
    }
}