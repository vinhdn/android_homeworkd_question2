package com.vinhdn.question2.base

interface IBaseView {
    /**
     * Show loading with text
     * @param text
     */
    fun showLoading(text: String? = null)

    /**
     * Hidden loading view showing
     */
    fun hideLoading()

    /**
     * Show error with toast or alert dialog
     * @param message
     * @param isToast
     */
    fun showError(message: String, isToast: Boolean = true)

    fun onRefresh()
    fun onLoadmore()
}