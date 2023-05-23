package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.wvtestapp.R
import com.test.wvtestapp.databinding.FragmentGardenBinding
import com.test.wvtestapp.gameUtils.GameUtils
import com.test.wvtestapp.model.Repo
import com.test.wvtestapp.ui.main.MainActivity
import com.test.wvtestapp.ui.main.MainViewModel
import java.util.Timer
import java.util.TimerTask

class GardenFragment : Fragment() {
    companion object {
        fun newInstance(): GardenFragment = GardenFragment()
    }

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    private val timer = Timer()
    private val repo = Repo().fruits

    var tmpCount = 0

    private var _binding: FragmentGardenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGardenBinding.inflate(layoutInflater)
        viewModel.initFruit()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameUtils = GameUtils(viewModel)

//        viewModel.initWallet()
        viewModel.fruits.observe(viewLifecycleOwner) { fruits ->
            binding.fruit1Count.text = fruits[0].count.toString()
            binding.fruit2Count.text = fruits[1].count.toString()
            binding.fruit3Count.text = fruits[2].count.toString()

            binding.itemPineapple.itemImage.setImageResource(
                gameUtils.reDrawField(0, fruits[0].fruitLevel)
            )
            binding.itemMelon.itemImage.setImageResource(
                gameUtils.reDrawField(1, fruits[1].fruitLevel)
            )
            binding.itemWatermelon.itemImage.setImageResource(
                gameUtils.reDrawField(2, fruits[2].fruitLevel)
            )

        }

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val fruitsLevels =
                    listOf((0..1).random(), (0..1).random(), (0..1).random())
                for (i in 0..fruitsLevels.lastIndex) {
                    if (fruitsLevels[i] == 1 && (repo[i].fruitLevel in 1..2)) {
                        val level = repo[i].fruitLevel + 1
                        repo[i].fruitLevel = level
                        viewModel.refreshFruitLevel(i, level)
                    }
                }
            }

        }, 10, 3000)

        binding.itemPineapple.itemImage.setOnClickListener {
            fieldClick(gameUtils, 0)
        }
        binding.itemMelon.itemImage.setOnClickListener {
            fieldClick(gameUtils, 1)
        }
        binding.itemWatermelon.itemImage.setOnClickListener {
            fieldClick(gameUtils, 2)
        }

        binding.btnBuySeeds.setOnClickListener {
            findNavController().navigate(R.id.seedsFragment)
        }
        binding.btnSellFruit.setOnClickListener {
            findNavController().navigate(R.id.fruitFragment)
        }

    }

    private fun fieldClick(gameUtils: GameUtils, field: Int) {
        if (gameUtils.canCollect(repo[field].fruitLevel)) {
            repo[field].fruitLevel = 0
//            gameUtils.harvestingCrop(field)
            viewModel.refreshFruitCount(field,(1..5).random())
            viewModel.refreshFruitLevel(field,0)

            Toast.makeText(
                requireContext(),
                getString(R.string.the_harvest_gathered),
                Toast.LENGTH_SHORT
            ).show()
        }
        if (gameUtils.isReadyForPlanting(repo[field].fruitLevel)) {
            repo[field].fruitLevel = 1
            gameUtils.plantSeeds(field)
            Toast.makeText(
                requireContext(),
                getString(R.string.seed_planted),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timer.cancel()
        timer.purge()
    }

}