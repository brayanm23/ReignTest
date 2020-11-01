package com.mindorks.example.coroutines.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.reign.database.AppDatabase
import com.test.reign.repository.PostRepository
import com.test.reign.viewmodel.PostsViewModel

class ViewModelFactory(private val repository: PostRepository, private val dbHelper: AppDatabase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PostsViewModel::class.java) -> {
                PostsViewModel(repository, dbHelper) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }

}