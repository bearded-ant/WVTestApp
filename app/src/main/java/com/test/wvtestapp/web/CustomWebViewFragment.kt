package com.test.wvtestapp.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.wvtestapp.databinding.FragmentWebviewBinding
import com.test.wvtestapp.ui.MainViewModel

class CustomWebViewFragment : Fragment() {
    companion object {
        fun newInstance(): CustomWebViewFragment = CustomWebViewFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    private var _binding: FragmentWebviewBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = binding.mainWebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        viewModel.remoteConfigUrl.observe(viewLifecycleOwner) {
            webView.loadUrl(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}