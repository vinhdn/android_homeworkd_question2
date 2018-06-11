package com.vinhdn.question2.injection.component

import com.vinhdn.question2.base.IBaseView
import com.vinhdn.question2.injection.module.ApiModule
import com.vinhdn.question2.injection.module.PresenterModule
import com.vinhdn.question2.ui.product.ProductFragment
import com.vinhdn.question2.ui.product.ProductPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class), (PresenterModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(productPresenter: ProductPresenter)
    fun inject(productFragment: ProductFragment)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun apiModule(networkModule: ApiModule): Builder

        fun presenterModule(presenterModule: PresenterModule): Builder

        @BindsInstance
        fun baseView(baseView: IBaseView): Builder
    }
}