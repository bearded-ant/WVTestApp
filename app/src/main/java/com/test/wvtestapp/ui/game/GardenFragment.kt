package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.wvtestapp.R
import com.test.wvtestapp.databinding.FragmentGardenBinding

class GardenFragment : Fragment() {
    companion object {
        fun newInstance(): GardenFragment = GardenFragment()
    }

    private var _binding: FragmentGardenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGardenBinding.inflate(layoutInflater)

        binding.btnBuySeeds.setOnClickListener {
            findNavController().navigate(R.id.seedsFragment)
        }
        binding.btnSellFruit.setOnClickListener {
            findNavController().navigate(R.id.seedsFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}