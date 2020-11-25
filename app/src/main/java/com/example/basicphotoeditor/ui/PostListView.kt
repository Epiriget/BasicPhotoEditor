package com.example.basicphotoeditor.ui

import com.example.basicphotoeditor.data.room.PostEntity

interface PostListView {
    // Todo: remove stab
    fun showPosts(posts: List<PostEntity>)
}