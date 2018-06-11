package com.vinhdn.question2.base

abstract class BasePresenter<out V : IBaseView>(protected val view: V): IBasePresenter