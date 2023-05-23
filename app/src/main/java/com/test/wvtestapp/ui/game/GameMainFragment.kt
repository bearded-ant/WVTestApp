package com.test.wvtestapp.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.wvtestapp.R
import com.test.wvtestapp.base.App
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

        val app = requireContext().applicationContext as App
        app.flyerInit()

        binding.btnStartGame.setOnClickListener {
            viewModel.initWallet()
            viewModel.initFruit()
            findNavController().navigate(R.id.gardenFragment)
        }

        binding.getAppsFlayer.setOnClickListener { showSnackbar() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showSnackbar() {
        val rootView: View = binding.root
        val snackbar = Snackbar.make(rootView, "AppsFlayer Info", Snackbar.LENGTH_SHORT)
        snackbar.setAction("Close") {
            snackbar.dismiss()
        }
        snackbar.show()
    }
}