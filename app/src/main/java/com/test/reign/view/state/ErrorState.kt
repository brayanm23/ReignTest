package com.test.reign.view.state

import android.view.View
import android.view.LayoutInflater.from
import com.test.reign.R.layout.content_error
import com.test.reign.extension.fadeIn
import com.test.reign.extension.fadeOut
import com.test.reign.view.PostsFragment
import com.test.reign.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.content_error.view.*
import kotlinx.android.synthetic.main.fragment_post.view.*

class ErrorState(private val viewModel: PostsViewModel) : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            view_loading.fadeOut()
            content_posts.fadeOut()
            view_error.apply {
                removeAllViews()
                addView(
                    from(context).inflate(content_error, null).apply {
                        retry_button.setOnClickListener {
                            viewModel.getPosts()
                        }
                    }
                )
                fadeIn()
            }
        }
    }
}