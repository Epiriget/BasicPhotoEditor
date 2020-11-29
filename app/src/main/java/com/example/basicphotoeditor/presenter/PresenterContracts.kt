package com.example.basicphotoeditor.presenter

import com.example.basicphotoeditor.ui.PostListViewContract

interface PresenterContract<V> {
    fun attachView(view: V)

    fun detachView()

    fun destroy()
}

interface PostListPresenterContract: PresenterContract<PostListViewContract> {
    fun supportStreamPosts()
}

