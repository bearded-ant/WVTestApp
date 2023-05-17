package com.test.wvtestapp.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.wvtestapp.databinding.FragmentGameMainBinding
import com.test.wvtestapp.databinding.FragmentSplashBinding

class GameMainFragment : Fragment() {
    companion object {
        fun newInstance(): GameMainFragment = GameMainFragment()
    }

    private var _binding: FragmentGameMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}