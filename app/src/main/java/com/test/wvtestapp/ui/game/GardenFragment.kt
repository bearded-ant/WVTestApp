package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.wvtestapp.R
import com.test.wvtestapp.databinding.FragmentGardenBinding
import com.test.wvtestapp.ui.main.MainViewModel
import java.util.Timer
import java.util.TimerTask

class GardenFragment : Fragment() {
    companion object {
        fun newInstance(): GardenFragment = GardenFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val timer = Timer()

    private var _binding: FragmentGardenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGardenBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initWallet()
        viewModel.userWallet.observe(viewLifecycleOwner) {
            binding.fruit1Count.text = it.fruits[0].count.toString()
            binding.fruit2Count.text = it.fruits[1].count.toString()
            binding.fruit3Count.text = it.fruits[2].count.toString()
        }


        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val gardenBedFruit1State = (0..1).random()
                viewModel.refreshTimer(gardenBedFruit1State)
//                binding.fruit1Count.text = gardenBedFruit1State.toString()
            }

        }, 10, 5000)

//        viewModel.refreshTimer(gardenBedFruit1State)
        viewModel.timer.observe(viewLifecycleOwner) {
            binding.fruit1Count.text = it.toString()
        }

        binding.btnBuySeeds.setOnClickListener {
            findNavController().navigate(R.id.seedsFragment)
        }
        binding.btnSellFruit.setOnClickListener {
            findNavController().navigate(R.id.seedsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timer.cancel()
        timer.purge()
    }

}