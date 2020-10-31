package com.test.reign.repository

import com.google.gson.Gson
import com.test.reign.api.PostService
import com.test.reign.api.RetrofitBuilder
import com.test.reign.model.PostResponse
import com.test.reign.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {

    var postService: PostService? = RetrofitBuilder.postService

    suspend fun getPosts(): Result<PostResponse> =
        withContext(Dispatchers.IO) {
            val response = postService?.getPosts()
            return@withContext if (response!!.isSuccessful) {
                Result.success(
                    Gson().fromJson(
                        response.body()?.charStream(),
                        PostResponse::class.java
                    )
                )
            } else
                Result.serverError()
        }
}