package com.example.basicphotoeditor.data.pagination

import androidx.paging.PagingSource
import androidx.paging.rxjava2.RxPagingSource
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.data.room.PostRoomDatabase
import com.example.basicphotoeditor.service.LentaService
import io.reactivex.Single

class PostPagingSource (private val database: PostRoomDatabase
                        ):PagingSource<Int, PostEntity>() {
    companion object {
        private const val DATABASE_STARTING_INDEX = 0
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostEntity> {
        val position = params.key ?: DATABASE_STARTING_INDEX

        val response = database.postsDao().getStreamPosts(position * params.loadSize, params.loadSize)
        return LoadResult.Page(
            data = response,
            prevKey = if(position == DATABASE_STARTING_INDEX) null else position - 1,
            nextKey = if(response.isEmpty()) null else position + 1
        )
    }

    fun setFilter() {

    }
}