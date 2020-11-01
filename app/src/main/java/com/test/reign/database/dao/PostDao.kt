package com.test.reign.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.reign.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM " + Post.TABLE_NAME)
    suspend fun getAll(): List<Post>

    @Query("DELETE FROM " + Post.TABLE_NAME + " WHERE ${Post.COLUMN_NAME_ID} = :id")
    suspend fun deleteById(id: String)
}