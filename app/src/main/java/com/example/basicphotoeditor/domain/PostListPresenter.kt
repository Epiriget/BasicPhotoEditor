package com.example.basicphotoeditor.domain

import android.app.Application
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.ui.PostListViewContract
import com.example.basicphotoeditor.ui.PostsListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PostListPresenter(application: Application):  PostListPresenterContract {
    // Todo: change to di
    private val repository = PostsRepository(application)
    private val disposables = CompositeDisposable()
    private var view: PostListViewContract? = null


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
    }

    override fun attachView(view: PostListViewContract) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}