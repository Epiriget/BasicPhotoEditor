package com.example.basicphotoeditor.domain

abstract class PresenterBase<T>: PresenterContract<T> {
    var view: T? = null
    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}