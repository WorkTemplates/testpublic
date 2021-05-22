package com.template.app.ui.webview

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.template.app.BuildConfig
import com.template.app.R

class WebViewFragment: Fragment(R.layout.webview_fragment) {

    private val args: WebViewFragmentArgs by navArgs()

    lateinit var webview: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview = view.findViewById(R.id.webview)
        webview.settings.javaScriptEnabled = true
        webview.settings.setGeolocationEnabled(true)
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.displayZoomControls = false
        webview.settings.builtInZoomControls = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        webview.webViewClient = WebViewClient()
        webview.webChromeClient = WebChromeClient()
        webview.loadUrl(args.url)
    }

    override fun onPause() {
        super.onPause()
        webview.onPause()
    }

    override fun onResume() {
        super.onResume()
        webview.onResume()
    }
}
