package com.example.basicphotoeditor.data

import android.app.Application
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.data.room.PostRoomDatabase
import io.reactivex.Flowable

class PostsRepository(application: Application) {
    private val database = PostRoomDatabase.getDatabaseInstance(application)
    private val dao = database.postsDao()

    fun getPosts(): Flowable<List<PostEntity>> {
        return dao.getPosts()
    }
}