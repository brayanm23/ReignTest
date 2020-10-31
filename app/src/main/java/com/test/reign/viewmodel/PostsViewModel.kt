package com.test.reign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reign.repository.PostRepository
import com.test.reign.core.Result
import com.test.reign.model.PostResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val _posts = MutableLiveData<Result<PostResponse>>()
    val post: LiveData<Result<PostResponse>>
        get() = _posts

    var postRepository = PostRepository()

    fun getPosts() {
        _posts.postValue(Result.loading())
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            _posts.postValue(postRepository.getPosts())
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _posts.postValue(Result.connectionError())
    }

    public override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
