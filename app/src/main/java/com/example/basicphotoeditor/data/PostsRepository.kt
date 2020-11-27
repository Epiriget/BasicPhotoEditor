package com.example.basicphotoeditor.data

import android.app.Application
import android.util.Log
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.data.room.PostRoomDatabase
import com.example.basicphotoeditor.service.LentaRss
import com.example.basicphotoeditor.service.LentaService
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PostsRepository(application: Application) {
    private val database = PostRoomDatabase.getDatabaseInstance(application)
    private val dao = database.postsDao()

    init {
        initDatabase()
    }

    companion object {
        private const val LENTA_SOURCE_NAME = "lenta.ru"
    }

    fun getPosts(): Flowable<List<PostEntity>> {
        return dao.getPosts()
    }

    private fun initDatabase() {
        val api = LentaService.create()
        val rawPosts = api.getPosts()
        val disposable = rawPosts
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        val posts = convert(it)
                        dao.insertPosts(posts)
                        Log.d("Retrofit", posts.size.toString())
                    },
                    {
                        Log.d("Retrofit", it.message.toString())
                    })
//        disposable.dispose()
    }

    private fun convert(rss: LentaRss): List<PostEntity> {
        return rss.channel.items.map {
            PostEntity(it.giud, it.enclosure.url, it.title, LENTA_SOURCE_NAME)
        }
    }
}