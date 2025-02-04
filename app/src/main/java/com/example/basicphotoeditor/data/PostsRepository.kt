package com.example.basicphotoeditor.data

import android.app.Application
import android.util.Log
import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.example.basicphotoeditor.data.pagination.PostPagingSource
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.data.room.PostRoomDatabase
import com.example.basicphotoeditor.service.LentaRss
import com.example.basicphotoeditor.service.LentaService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

class PostsRepository(application: Application) {
    private val database = PostRoomDatabase.getDatabaseInstance(application)
    private val dao = database.postsDao()

    init {
        initDatabase()
    }

    companion object {
        private const val LENTA_SOURCE_NAME = "lenta.ru"
        private const val DATABASE_PAGE_SIZE = 20
    }


    fun getPage(): Flow<PagingData<PostEntity>> {
        return Pager(
            config = PagingConfig(DATABASE_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(database) }
        ).flow
    }

    private fun initDatabase() {
        val api = LentaService.create()

        api.getPosts()
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
    }

    private fun convert(rss: LentaRss): List<PostEntity> {
        return rss.channel.items.map {
            PostEntity(it.giud, it.enclosure.url, it.title, LENTA_SOURCE_NAME)
        }
    }
}