package com.example.basicphotoeditor.presenter

import android.app.Application
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.ui.PostListViewContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

    override fun supportStreamPosts() {
        disposables.add(repository.getPage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showStreamPosts(it)
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