package com.test.reign.view.state

import android.os.Bundle
import android.view.LayoutInflater.from
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.test.reign.extension.fadeOut
import com.test.reign.model.Post
import com.test.reign.R.id.action_listFragment_to_detailFragment
import com.test.reign.extension.fadeIn
import com.test.reign.R
import com.test.reign.view.DeletePostInterface
import com.test.reign.view.PostsFragment
import com.test.reign.view.adapter.PostAdapter
import com.test.reign.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.content_posts.view.*
import kotlinx.android.synthetic.main.fragment_post.view.*

class SuccessState(private val viewModel: PostsViewModel, private val response: List<Post>) : PostsViewState {

    companion object {
        const val URL_KEY = "url_key"
        const val TITLE_KEY = "title_key"
    }

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            view_loading.fadeOut()
            view_error.fadeOut()
            content_posts.apply {
                removeAllViews()
                addView(from(context).inflate(R.layout.content_posts, null))
                fadeIn()
            }
            swipe_refresh_layout.isRefreshing = false
            setOnRefreshListener(view.rootView)
            setRecyclerView(posts_recyclerview)
        }
    }

    private fun setRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostAdapter(object : DeletePostInterface {
                override fun deletePost(id: String) { viewModel.deleteById(id) }
            })
            (adapter as PostAdapter).setRecyclerViewItemClick { view, post ->
                val bundle = Bundle().apply {
                    putString(URL_KEY, post.story_url)
                    putString(TITLE_KEY, post.story_title)
                }
                view.findNavController().navigate(action_listFragment_to_detailFragment, bundle)
            }
            (adapter as PostAdapter).addAll(response, true)
            (adapter as PostAdapter).notifyDataSetChanged()

            (adapter as PostAdapter).enableSwipeAction(posts_recyclerview)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }
    }

    private fun setOnRefreshListener(view: View) {
        view.swipe_refresh_layout.setOnRefreshListener {
            viewModel.getPosts()
        }
    }
}