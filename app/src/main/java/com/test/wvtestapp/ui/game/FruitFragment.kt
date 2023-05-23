package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.wvtestapp.databinding.FragmentFruitBinding
import com.test.wvtestapp.ui.main.MainViewModel

class FruitFragment : Fragment() {
    companion object {
        fun newInstance(): FruitFragment = FruitFragment()
    }

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
//    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    private var _binding: FragmentFruitBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitBinding.inflate(layoutInflater)
        viewModel.initWallet()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fruits.observe(viewLifecycleOwner) {
            binding.pineappleCount.text = it[0].count.toString()
            binding.melonCount.text = it[1].count.toString()
            binding.waterMelonCount.text = it[2].count.toString()
        }
        viewModel.userWallet.observe(this) {
            binding.walletCost.text = it.coins.toString()
        }

        binding.btnSellMelon.setOnClickListener {
            val fruitCount = binding.melonCount.text.toString().toInt()
            sellClick(0, fruitCount)
        }
        binding.btnSellPineapple.setOnClickListener {
            val fruitCount = binding.pineappleCount.text.toString().toInt()
            sellClick(1, fruitCount)
        }
        binding.btnSellWatermelon.setOnClickListener {
            val fruitCount = binding.waterMelonCount.text.toString().toInt()
            sellClick(2, fruitCount)
        }

    }

    private fun sellClick(field:Int, fruitCutout: Int) {
        if (fruitCutout > 0) {
            viewModel.refreshWallet(fruitCutout)
            viewModel.refreshFruitCount(field, -fruitCutout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}