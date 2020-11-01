package com.test.reign.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reign.core.Status.CONNECTION_ERROR
import com.test.reign.core.Status.SUCCESS
import com.test.reign.database.AppDatabase
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

class PostsViewModel(private val postRepository: PostRepository, private val db: AppDatabase) : ViewModel() {

    private lateinit var model: PostResponse

    private val _posts = MutableLiveData<PostsViewState>()
    val post: LiveData<PostsViewState>
        get() = _posts

    fun getPosts() {
        _posts.postValue(LoadingState())
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            val response = postRepository.getPosts()
            when {
                response.status == SUCCESS -> {
                    model = response.data!!
                    db.postDao().insertAll(model.hits)
                    _posts.postValue(SuccessState(this@PostsViewModel, model.hits))
                }
                response.status == CONNECTION_ERROR && db.postDao().getAll().isNotEmpty() -> {
                    val posts = db.postDao().getAll()
                    _posts.postValue(SuccessState(this@PostsViewModel, posts))
                }
                else -> _posts.postValue(ErrorState(this@PostsViewModel))
            }
        }
    }

    fun deleteById(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            db.postDao().deleteById(id)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        viewModelScope.launch(Dispatchers.Main) {
            if (db.postDao().getAll().isNotEmpty()) {
                val posts = db.postDao().getAll()
                _posts.postValue(SuccessState(this@PostsViewModel, posts))
            } else
                _posts.postValue(ErrorState(this@PostsViewModel))
        }
    }

    public override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
