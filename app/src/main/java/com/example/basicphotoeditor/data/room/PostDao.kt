package com.example.basicphotoeditor.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts")
    fun getPosts(): Flowable<List<PostEntity>>

    @Query("DELETE FROM posts")
    fun deletePosts()
}