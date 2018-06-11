package com.vinhdn.question2.base

interface IBasePresenter {
    //on View attached to Presenter
    fun attachView()
    //on View detached to Presenter
    fun detachView()
}