package com.example.basicphotoeditor.domain

import android.view.View
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.ui.PostListView
import io.reactivex.Flowable
import java.util.*

// Todo: implement wildcard like in java: <V extends MvpView>
interface PresenterContract<V> {
    fun attachView(view: V)

//    fun viewIsReady()

    fun detachView()

    fun destroy()
}

interface PostListPresenterContract: PresenterContract<PostListView> {
    fun supportPosts()
}

