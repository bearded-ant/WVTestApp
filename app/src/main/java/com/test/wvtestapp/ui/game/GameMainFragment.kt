package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.wvtestapp.R
import com.test.wvtestapp.databinding.FragmentGameMainBinding
import com.test.wvtestapp.ui.main.MainViewModel

class GameMainFragment : Fragment() {
    companion object {
        fun newInstance(): GameMainFragment = GameMainFragment()
    }

    private var _binding: FragmentGameMainBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMainBinding.inflate(layoutInflater)

        binding.btnStartGame.setOnClickListener {
            viewModel.initWallet()
            viewModel.initFruit()
            findNavController().navigate(R.id.gardenFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}