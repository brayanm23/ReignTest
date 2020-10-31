package com.test.reign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.reign.R
import com.test.reign.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostsFragment : Fragment() {

    companion object {
        fun newInstance() = PostsFragment()
    }

    private lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setPostsObserver()
        viewModel.getPosts()
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(PostsViewModel::class.java)
    }

    private fun setPostsObserver() {
        viewModel.post.observe(viewLifecycleOwner, Observer { response ->
            response.data?.let { size.text = it.hits.size.toString()}
            /*when (response.status) {
                LOADING -> showLoading()
                SUCCESS -> setResponse(response.data)
                SERVER_ERROR -> showError(PROBLEM)
                CONNECTION_ERROR -> showError(NETWORK)
            }*/
        })
    }


}
