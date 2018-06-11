package com.vinhdn.question2.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinhdn.question2.helper.extension.showConfirm
import com.vinhdn.question2.helper.extension.toast
import com.vinhdn.question2.injection.component.DaggerPresenterInjector
import com.vinhdn.question2.injection.component.PresenterInjector
import com.vinhdn.question2.injection.module.ApiModule
import com.vinhdn.question2.injection.module.PresenterModule
import com.vinhdn.question2.ui.product.ProductFragment
import kotlinx.android.synthetic.main.fragment_product.view.*

abstract class BaseFragment<P : BasePresenter<IBaseView>> : IBaseView, Fragment() {

    protected lateinit var presenter: P
    private lateinit var mActivity: BaseActivity
    protected abstract fun getLayoutRes(): Int
    private lateinit var rootView: View

    /**
     * Base presenter any presenter of the application must extend.
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .apiModule(ApiModule)
            .presenterModule(PresenterModule())
            .baseView(baseView())
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ProductFragment -> injector.inject(this)
        }
    }

    private fun baseView(): IBaseView {
        return when (this) {
            is ProductFragment -> this
            else -> {
                this
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is BaseActivity) {
            Throwable("Activity no override BaseActivity")
        }
        mActivity = activity as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutRes(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = instantiatePresenter()
        presenter.attachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading(text: String?) {
        activity?.runOnUiThread {
            rootView.progressLayout?.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        activity?.runOnUiThread {
            rootView.progressLayout?.visibility = View.GONE
        }
    }

    /**
     * Instantiates the presenter the Fragment is ViewCreated.
     */
    protected abstract fun instantiatePresenter(): P

    override fun showError(message: String, isToast: Boolean) {
        if (isToast) {
            toast(message)
        } else {
            showAlert(message)
        }
    }

    private fun showAlert(message: String, handler: (() -> Unit)? = null) {
        activity?.runOnUiThread {
            context?.let {
                showConfirm(it, null, message, rightButtonClickHandler = handler)
            }
        }
    }

    override fun onLoadmore() {}
    override fun onRefresh() {}
}