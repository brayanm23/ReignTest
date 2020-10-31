package com.test.reign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reign.core.Status.SUCCESS
import com.test.reign.view.state.SuccessState
import com.test.reign.repository.PostRepository
import com.test.reign.model.PostResponse
import com.test.reign.view.state.ErrorState
import com.test.reign.view.state.LoadingState
import com.test.reign.view.state.PostsViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {

    private lateinit var model: PostResponse

    private val _posts = MutableLiveData<PostsViewState>()
    val post: LiveData<PostsViewState>
        get() = _posts

    var postRepository = PostRepository()

    fun getPosts() {
        _posts.postValue(LoadingState())
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            val response = postRepository.getPosts()

            if (response.status == SUCCESS) {
                model = response.data!!
                _posts.postValue(SuccessState(this@PostsViewModel, model))
            } else {
                _posts.postValue(ErrorState(this@PostsViewModel))
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _posts.postValue(ErrorState(this@PostsViewModel))
    }

    public override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
