package com.test.reign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.reign.R
import com.test.reign.view.adapter.PostAdapter.Companion.TITLE_KEY
import com.test.reign.view.adapter.PostAdapter.Companion.URL_KEY
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        detail_web_view.loadUrl(arguments?.getString(URL_KEY))
        toolbar_title.text = arguments?.getString(TITLE_KEY)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setToolbar() {
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
