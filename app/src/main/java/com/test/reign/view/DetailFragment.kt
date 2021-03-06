package com.test.reign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.reign.R
import com.test.reign.view.state.SuccessState.Companion.TITLE_KEY
import com.test.reign.view.state.SuccessState.Companion.URL_KEY
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        detail_web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                detail_web_view.visibility = View.VISIBLE
                progress_bar.visibility = View.INVISIBLE
            }
        }
        val settings: WebSettings = detail_web_view.settings
        settings.javaScriptEnabled = true
        detail_web_view.loadUrl(arguments?.getString(URL_KEY))
        toolbar_title.text = arguments?.getString(TITLE_KEY)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initToolbar() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).let {
                it.apply {
                    setSupportActionBar(toolbar)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
            }
        }
    }
}
