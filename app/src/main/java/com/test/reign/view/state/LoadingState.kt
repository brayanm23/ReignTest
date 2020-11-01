package com.test.reign.view.state

import android.view.LayoutInflater.from
import android.view.View
import com.test.reign.extension.fadeIn
import com.test.reign.extension.fadeOut
import com.test.reign.view.PostsFragment
import com.test.reign.R.layout.content_loading
import kotlinx.android.synthetic.main.fragment_post.view.*

class LoadingState : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            view_loading.removeAllViews()
            view_loading.addView(from(context).inflate(content_loading, null))
            view_loading.fadeIn()
            view_error.fadeOut()
            //home_content_layout.fadeOut()
        }
    }
}
