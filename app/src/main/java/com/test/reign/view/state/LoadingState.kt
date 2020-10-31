package com.test.reign.view.state

import android.view.View
import com.test.reign.view.PostsFragment

class LoadingState : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        with(view) {
            /*progress_bar.fadeIn()
            content_error.fadeOut()
            home_content_layout.fadeOut()*/
        }
    }
}
