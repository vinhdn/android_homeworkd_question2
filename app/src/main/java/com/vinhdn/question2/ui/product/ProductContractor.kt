package com.vinhdn.question2.ui.product

import com.vinhdn.question2.base.BasePresenter
import com.vinhdn.question2.base.IBasePresenter
import com.vinhdn.question2.base.IBaseView
import com.vinhdn.question2.model.Product

interface ProductContractor {
    interface View: IBaseView {
        /**
         * Return list product, that is response form API getListProduct
         * @param listProduct list Product of server
         * @param isLoadmore true if load data of page > 1
         */
        fun showListProduct(listProduct: List<Product>, isLoadmore: Boolean = false)
    }

    interface Presenter: IBasePresenter {
        fun getListProduct(page: Int = 1, isRefresh: Boolean = true)
    }
}