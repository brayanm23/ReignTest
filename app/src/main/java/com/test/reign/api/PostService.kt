package com.test.reign.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("search_by_date?query=android")
    suspend fun getPosts(): Response<ResponseBody>
}
