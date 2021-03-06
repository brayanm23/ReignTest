package com.test.reign.view.state

import android.view.View
import com.test.reign.extension.fadeIn
import com.test.reign.extension.fadeOut
import com.test.reign.view.PostsFragment
import kotlinx.android.synthetic.main.fragment_post.view.*

class LoadingState : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            view_error.fadeOut()
            content_posts.fadeOut()
            progress_bar.fadeIn()
        }
    }
}
