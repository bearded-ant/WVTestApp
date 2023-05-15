package com.test.wvtestapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.test.wvtestapp.databinding.FragmentWebviewBinding

class CustomWebViewFragment : Fragment() {
    companion object {
        fun newInstance(): CustomWebViewFragment = CustomWebViewFragment()
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

        webView.loadUrl("https://google.com")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}