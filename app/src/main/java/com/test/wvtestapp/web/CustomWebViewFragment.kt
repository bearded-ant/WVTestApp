package com.test.wvtestapp.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.wvtestapp.R
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
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.setSupportZoom(false)
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        viewModel.remoteConfigUrl.observe(viewLifecycleOwner) {
            webView.loadUrl(it)
        }

        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK &&
                    event.action === MotionEvent.ACTION_UP &&
                    webView.canGoBack() && webView.url != "string"
                ) {
                    webView.goBack()
                    return true
                }
                return false
            }
        })

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    AlertDialog.Builder(requireContext()).apply {
                        setTitle(resources.getString(R.string.exit_title))
                        setMessage(resources.getString(R.string.exit_question))
                        setPositiveButton(resources.getString(R.string.exit_yes)) { _, _ ->
                            requireActivity().finish()
                        }
                        setNegativeButton(resources.getString(R.string.exit_no)) { _, _ ->
                        }
                        setCancelable(true)
                    }.create().show()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}