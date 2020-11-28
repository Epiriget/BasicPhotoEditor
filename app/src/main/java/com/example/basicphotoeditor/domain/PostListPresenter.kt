package com.example.basicphotoeditor.domain

import android.app.Application
import androidx.paging.PagingData
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.ui.PostListViewContract
import com.example.basicphotoeditor.ui.PostsListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import okhttp3.Dispatcher

class PostListPresenter(application: Application):  PostListPresenterContract {
    // Todo: change to di
    private val repository = PostsRepository(application)
    private val disposables = CompositeDisposable()
    private var view: PostListViewContract? = null

    private var currentSearchResult: Flow<PagingData<PostEntity>>? = null


    override fun supportPosts() {
        disposables.add(repository.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showPosts(it)
            }
        )
    }

    override fun supportStreamPosts() {
        view?.showStreamPosts(repository.getPage())
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