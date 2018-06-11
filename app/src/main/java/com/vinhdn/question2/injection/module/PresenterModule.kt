package com.vinhdn.question2.injection.module

import com.vinhdn.question2.BuildConfig
import com.vinhdn.question2.base.IBaseView
import com.vinhdn.question2.manager.network.ProductApi
import com.vinhdn.question2.ui.product.ProductContractor
import com.vinhdn.question2.ui.product.ProductPresenter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class  PresenterModule {

    /**
     * Provides the ProductPresenter
     * @return the ProductPresenter
     */
    @Provides
    @Reusable
    fun provideProductPresenter(view: IBaseView, api: ProductApi): ProductPresenter {
        return ProductPresenter(view as ProductContractor.View, api)
    }

}