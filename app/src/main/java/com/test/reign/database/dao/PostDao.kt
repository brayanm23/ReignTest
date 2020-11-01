package com.test.reign.database.dao

import androidx.room.*
import com.test.reign.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM " + Post.TABLE_NAME + " WHERE "+ Post.COLUMN_NAME_IS_DELETE+"= 0")
    suspend fun getAll(): List<Post>

    @Update
    suspend fun deleteById(post: Post)
}