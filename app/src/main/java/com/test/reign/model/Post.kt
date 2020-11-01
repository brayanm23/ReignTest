package com.test.reign.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = Post.TABLE_NAME)
data class Post (
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_ID)
    val objectID: String,
    val created_at: String,
    val author: String,
    val story_title: String,
    val story_url: String,
    var isDelete: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "post"
        const val COLUMN_NAME_ID = "id"
    }
}