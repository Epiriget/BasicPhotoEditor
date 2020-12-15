package com.example.basicphotoeditor.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.basicphotoeditor.data.room.PostEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostUseCase {
    companion object {
        fun convert(
            posts: Flow<PagingData<PostEntity>>,
            filter: FilterTransformation.Filter
        ): Flow<PagingData<Post>> {
            return posts
                .map { pager ->
                    pager.map { post ->
                        Post(post.id, post.image, filter, post.title, post.source)
                    }
                }
        }
    }
}


