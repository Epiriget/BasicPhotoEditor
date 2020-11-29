package com.example.basicphotoeditor.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts LIMIT :loadSize OFFSET :position")
    suspend fun getPagedPosts(position:Int, loadSize: Int): List<PostEntity>

    @Query("DELETE FROM posts")
    fun deletePosts()
}