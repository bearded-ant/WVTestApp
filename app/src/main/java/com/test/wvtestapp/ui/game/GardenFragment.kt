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
import java.util.*

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
                val gardenGrowing =
                    listOf((0..1).random(), (0..1).random(), (0..1).random())

                val melon = binding.itemFruit1.itemImage
                val watermelon = binding.itemFruit2.itemImage
                val pineapple = binding.itemFruit3.itemImage

                viewModel.fruits.observe(viewLifecycleOwner) {
                    for (i in 0..gardenGrowing.lastIndex) {
                        if (gardenGrowing[i] == 1 && (it[i].growing in 1..2))
                            fruitGrowingUpdate(i)
                    }
                }
                viewModel.refreshGrowingState(gardenGrowing)
            }

        }, 10, 5000)



        viewModel.growing.observe(viewLifecycleOwner) {
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

    fun fruitGrowingUpdate(fruitNumber:Int) {
        when (fruitNumber) {
            1->""
        }
    }

}