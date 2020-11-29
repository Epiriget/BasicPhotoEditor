package com.example.basicphotoeditor.ui

import androidx.paging.PagingData
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.domain.Post
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface PostListViewContract: ViewContract {
    fun showStreamPosts(posts: PagingData<Post>)
}

interface ViewContract {

}

