package com.example.usbankinterview.ui.coin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.usbankinterview.R
import com.example.usbankinterview.databinding.FragmentBitValueBinding
import com.example.usbankinterview.service.FetchDataService

class CoinFragment: Fragment(), CoinContract.View {

    var viewBinding: FragmentBitValueBinding? = null
    private val coinPresenter: CoinPresenter = CoinPresenter(this)

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(content: Context?, intent: Intent?) {
            val value = intent?.extras?.getString("PRICE")
            val fromLast = intent?.extras?.getInt("FROM_LAST")
            viewBinding?.valueText?.text = value
            when(fromLast) {
                0 -> viewBinding?.valueText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                -1 -> viewBinding?.valueText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                1 -> viewBinding?.valueText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBitValueBinding.inflate(inflater)
        setUpView()
        return viewBinding?.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().registerReceiver(receiver, IntentFilter("BIT_COIN_VALUE"))
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(receiver)
    }

    override fun setDataToText(value: String?) {
        requireActivity().runOnUiThread {
            viewBinding?.valueText?.text = value
        }
    }

    private fun setUpView() {
        viewBinding?.fetchDataBtn?.setOnClickListener {
            coinPresenter.fetchBitcoinData()
        }
        viewBinding?.startServiceBtn?.setOnClickListener {
            requireActivity().startService(Intent(requireActivity(), FetchDataService::class.java))
        }

        viewBinding?.stopServiceBtn?.setOnClickListener {
            requireActivity().stopService(Intent(requireActivity(), FetchDataService::class.java))
        }
    }
}