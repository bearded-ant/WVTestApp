package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.wvtestapp.R
import com.test.wvtestapp.databinding.FragmentSeedsBinding
import com.test.wvtestapp.ui.main.MainViewModel

class SeedsFragment : Fragment() {
    companion object {
        fun newInstance(): SeedsFragment = SeedsFragment()
    }

    private var _binding: FragmentSeedsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeedsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userWallet.observe(viewLifecycleOwner) {
            binding.coins.text = it.coins.toString()
        }

        viewModel.fruits.observe(viewLifecycleOwner) { fruit ->
            binding.pineappleSeedsCount.text = fruit[0].seedCount.toString()
            binding.melonSeedCount.text = fruit[1].seedCount.toString()
            binding.watermelonSeedsCount.text = fruit[2].seedCount.toString()
        }

        binding.btnBuyPineappleSeed.setOnClickListener {
            buyClick(0)
        }
        binding.btnBuyMelonSeed.setOnClickListener {
            buyClick(1)
        }
        binding.btnBuyWatermelonSeed.setOnClickListener {
            buyClick(2)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.gardenFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun buyClick(field: Int) {
        val wallet = binding.coins.text.toString().toInt()
        if (wallet > 0) {
            viewModel.refreshSeedCount(field, 1)
            viewModel.refreshWallet(-1)
        }
    }

}