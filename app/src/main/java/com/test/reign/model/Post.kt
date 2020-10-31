package com.test.reign.model

data class Post (
    val story_id: String,
    val created_at: String,
    val author: String,
    val story_title: String,
    val story_url: String,
    var isExpanded: Boolean = false
)