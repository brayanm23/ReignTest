package com.test.reign.view.state

import android.view.View
import com.test.reign.view.PostsFragment
import com.test.reign.viewmodel.PostsViewModel

class ErrorState(private val viewModel: PostsViewModel) : PostsViewState {

    override fun setState(view: View, postsFragment: PostsFragment) {
        TODO("Not yet implemented")
    }
}
    /*companion object {
        private const val EVENT_HOME_ERROR = "Home - Error"
        private const val ERROR = "error"
        const val NETWORK = "internet"
        const val PROBLEM = "problema"
    }

    override fun setState(binding: FragmentHomeBinding, homeFragment: HomeFragment) {
        execute(TrackAction(TrackData(path = EVENT_HOME_ERROR, params = mapOf(ERROR to errorType))))

        with(binding.root) {
            progress_bar.fadeOut()
            home_content_layout.fadeOut()
            content_error.removeChildrenIfAny()

            // TODO FeedbackFragment
            content_error.addView(
                ContentGenericErrorBinding.inflate(from(context)).apply {
                    retryButton.setOnClickListener {
                        homeFragment.sharedPreferences.apply {
                            viewModel.getHome(
                                getString(HomeFragment.MONEY_BUTTON_ANIMATED_DATE_KEY, null),
                                getInt(HomeFragment.MONEY_BUTTON_ANIMATED_COUNT_KEY, 0)
                            )
                        }
                    }
                }.root
            )

            content_error.fadeIn()
            swipe_refresh_layout.isRefreshing = false
        }
    }*/
