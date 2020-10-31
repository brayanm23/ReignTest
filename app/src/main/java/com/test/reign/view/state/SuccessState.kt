package com.test.reign.view.state

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.test.reign.model.PostResponse
import com.test.reign.view.PostsFragment
import com.test.reign.view.adapter.PostAdapter
import com.test.reign.viewmodel.PostsViewModel
import io.sulek.ssml.SSMLLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_post.view.*

class SuccessState(private val viewModel: PostsViewModel, private val response: PostResponse) : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            swipe_refresh_layout.isRefreshing = false
            setOnRefreshListener(view.rootView)
            posts_recyclerview.apply {
                layoutManager = SSMLLinearLayoutManager(context)
                adapter = PostAdapter(response.hits)
                addItemDecoration(DividerItemDecoration(context, VERTICAL))
            }
        }

    }

    private fun setOnRefreshListener(view: View) {
        view.swipe_refresh_layout.setOnRefreshListener {
            viewModel.getPosts()
        }
    }
}