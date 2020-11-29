package com.example.basicphotoeditor.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.basicphotoeditor.data.room.PostEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PostUseCase {
    companion object {
        fun convert(
            posts: Flowable<PagingData<PostEntity>>,
            filter: FilterTransformation.Filter
        ): Flowable<PagingData<Post>> {
            return posts
                .observeOn(Schedulers.io())
                .map { pager ->
                    pager.map { post ->
                        Post(post.id, post.image, filter, post.title, post.source)
                    }
                }
        }
    }
}


