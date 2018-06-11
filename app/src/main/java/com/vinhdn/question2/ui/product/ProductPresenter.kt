package com.vinhdn.question2.ui.product

import com.vinhdn.question2.base.BasePresenter
import com.vinhdn.question2.manager.network.ProductApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject

class ProductPresenter @Inject constructor(productView: ProductContractor.View,
                                           private var productApi: ProductApi): ProductContractor.Presenter, BasePresenter<ProductContractor.View>(productView) {

    private var disposable: Disposable? = null
    private var self: WeakReference<ProductContractor.View?>? = null
    override fun attachView() {
        self = WeakReference(view)
    }

    /**
     * Dispose load data from api if view is detached
     */
    override fun detachView() {
        disposable?.dispose()
        self?.clear()
        self = null
    }


    /**
     * Loads the Products from the API and presents them in the view when retrieved, or shows error if have any error
     */
    override fun getListProduct(page: Int, isRefresh: Boolean) {
        if (isRefresh)
            self?.get()?.showLoading()
        disposable = productApi.getListProduct().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ productResponse ->
                    if (productResponse.data != null) {
                        self?.get()?.showListProduct(productResponse.data!!, !isRefresh)
                    } else {
                        self?.get()?.showError(productResponse.errorMessage)
                    }
                    self?.get()?.hideLoading()
                }, {
                    self?.get()?.showError(it.message ?: "Have an error")
                    self?.get()?.hideLoading()
                })
    }

}