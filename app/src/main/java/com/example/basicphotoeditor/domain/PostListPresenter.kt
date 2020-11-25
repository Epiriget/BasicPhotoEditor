package com.example.basicphotoeditor.domain

import android.app.Application
import android.view.View
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.ui.PostListView
import com.example.basicphotoeditor.ui.PostsListFragment
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PostListPresenter(application: Application): PresenterBase<PostListView>(), PostListPresenterContract {
    // Todo: change to di
    private val repository = PostsRepository(application)
    private val disposables = CompositeDisposable()

    init {
        supportPosts()
    }

    override fun supportPosts() {
        disposables.add(repository.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showPosts(it)
            }
        )
    }

    override fun destroy() {
        disposables.dispose()
        super.destroy()
    }
}