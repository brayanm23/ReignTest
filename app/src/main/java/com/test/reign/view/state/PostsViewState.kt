package com.test.reign.view.state

import android.view.View
import com.test.reign.view.PostsFragment

interface PostsViewState {
    fun setState(view: View, postsFragment: PostsFragment)
}
