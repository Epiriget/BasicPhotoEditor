package com.example.basicphotoeditor.presenter

abstract class PresenterBase<T>: PresenterContract<T> {
    var view: T? = null
    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}