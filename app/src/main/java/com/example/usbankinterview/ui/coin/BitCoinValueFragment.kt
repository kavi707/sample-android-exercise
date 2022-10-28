package com.example.usbankinterview.ui.coin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.usbankinterview.databinding.FragmentBitValueBinding
import com.example.usbankinterview.service.FetchDataService

class BitCoinValueFragment: Fragment(), CoinContract.View {

    var viewBinding: FragmentBitValueBinding? = null
    private val coinPresenter: CoinPresenter = CoinPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBitValueBinding.inflate(inflater)
        setUpView()
        return viewBinding?.root
    }

    override fun setDataToText(value: String?) {

        viewBinding?.startServiceBtn?.setOnClickListener {

        }

        viewBinding?.stopServiceBtn?.setOnClickListener {

        }

        requireActivity().runOnUiThread {
            viewBinding?.valueText?.text = value
        }
    }

    private fun setUpView() {
        viewBinding?.fetchDataBtn?.setOnClickListener {
            coinPresenter.fetchBitcoinData()
        }
    }
}