package com.example.basicphotoeditor.domain

import com.example.basicphotoeditor.ui.PostListViewContract
import com.example.basicphotoeditor.ui.ViewContract

// Todo: implement wildcard like in java: <V extends MvpView>
interface PresenterContract<V> {
    fun attachView(view: V)

//    fun viewIsReady()

    fun detachView()

    fun destroy()
}

interface PostListPresenterContract: PresenterContract<PostListViewContract> {
    fun supportPosts()
}

