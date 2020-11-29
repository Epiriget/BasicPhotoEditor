package com.example.basicphotoeditor.presenter

import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.ui.PostListViewContract

// Todo: implement wildcard like in java: <V extends MvpView>
interface PresenterContract<V> {
    fun attachView(view: V)

    fun detachView()

    fun destroy()
}

interface PostListPresenterContract: PresenterContract<PostListViewContract> {
    fun supportStreamPosts()
}

