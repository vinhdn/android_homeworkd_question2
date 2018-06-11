package com.vinhdn.question2.ui.product

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.vinhdn.question2.R
import com.vinhdn.question2.base.BaseFragment
import com.vinhdn.question2.model.Product
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductFragment: BaseFragment<ProductPresenter>(), ProductContractor.View {

    @Inject
    lateinit var iPresenter: ProductPresenter

    //Override function to return id of layout fragment
    override fun getLayoutRes() = R.layout.fragment_product

    //Override function to return presenter of fragment
    override fun instantiatePresenter() = iPresenter

    private lateinit var adapter: ProductAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    //Current load page
    private var page = 1

    //Max item hidden of view to load more data
    private val maxItemHiddenToLoadmore = 1

    //the variable lastVisibleItemPosition is return last visible of recyclerView layout manager
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    var scrollListener: RecyclerView.OnScrollListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(this.context)
        recyclerView?.layoutManager = linearLayoutManager
        adapter = ProductAdapter(listOf())
        recyclerView?.adapter = adapter
        presenter.getListProduct()
        refreshLayout?.setOnRefreshListener {
            this.onRefresh()
        }
        initLoadmore()
    }

    override fun onRefresh() {
        presenter.getListProduct()
    }

    override fun onLoadmore() {
        //If is loading data return, no action
        if(refreshLayout?.isRefreshing == true) {
            return
        }
        refreshLayout?.isRefreshing = true
        page += 1
        presenter.getListProduct(page, false)
    }

    override fun hideLoading() {
        super.hideLoading()
        refreshLayout?.isRefreshing = false
    }

    override fun showListProduct(listProduct: List<Product>, isLoadmore: Boolean) {
        if (!isLoadmore) {
            page = 1
            //If refresh data replace all data in adapter
            activity?.runOnUiThread {
                adapter.listProduct = listProduct
                adapter.notifyDataSetChanged()
            }
        } else {
            //If load more data append new data in old data of adapter
            val listData = adapter.listProduct.toMutableList()
            listData.addAll(listProduct)
            activity?.runOnUiThread {
                adapter.listProduct = listData
                adapter.notifyDataSetChanged()
            }
        }
        //If response data have size equal page size enable listener load more
        if (listProduct.size >= 10) {
            recyclerView?.addOnScrollListener(scrollListener)
        }
    }


    private fun initLoadmore() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager.itemCount
                //Check number hidden item in adapter is
                if (totalItemCount == lastVisibleItemPosition + maxItemHiddenToLoadmore) {
                    //Remove listener load more if is start loading more
                    recyclerView.removeOnScrollListener(scrollListener)
                    this@ProductFragment.onLoadmore()
                }
            }
        }
    }
}