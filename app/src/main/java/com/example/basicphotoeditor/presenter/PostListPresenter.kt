package com.example.basicphotoeditor.presenter

import android.app.Application
import androidx.paging.PagingData
import com.example.basicphotoeditor.data.PostsRepository
import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.domain.PostUseCase
import com.example.basicphotoeditor.domain.Post
import com.example.basicphotoeditor.ui.PostListViewContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostListPresenter(application: Application):  PostListPresenterContract {
    // Todo: change to di
    private val repository = PostsRepository(application)
    private val disposables = CompositeDisposable()
    private var view: PostListViewContract? = null

    private var currentFilter: FilterTransformation.Filter = FilterTransformation.Filter.NONE
    private var currentPage: PagingData<Post>? = null


    override fun supportStreamPosts() {
        disposables.add(PostUseCase.convert(repository.getPage(), currentFilter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                currentPage = it
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