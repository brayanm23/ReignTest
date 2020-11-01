package com.test.reign.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.reign.database.dao.PostDao
import com.test.reign.model.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val NAME = "REIGN_DB"
    }

    abstract fun postDao(): PostDao

}