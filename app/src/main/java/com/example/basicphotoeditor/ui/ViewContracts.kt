package com.example.basicphotoeditor.ui

import com.example.basicphotoeditor.data.room.PostEntity

interface PostListViewContract: ViewContract {
    fun showPosts(posts: List<PostEntity>)
}

interface ViewContract {

}

