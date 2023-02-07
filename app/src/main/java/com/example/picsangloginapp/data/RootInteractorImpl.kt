package com.example.picsangloginapp.data

import com.best.core.Feature
import com.best.core.di.CoreComponentHolder
import com.best.login_api.di.LoginComponentDependencies
import com.best.login_feature.LoginFeature
import com.best.login_feature.di.LoginFeatureComponentDependencies
import com.best.login_feature.di.LoginFeatureComponentHolder
import com.best.login_provider.loginComponentHolder
import com.example.picsangloginapp.domain.RootInteractor

class RootInteractorImpl : RootInteractor {
    override fun initFeature(feature: Feature) {
        when (feature::class) {
            LoginFeature::class -> initLogin()
        }
    }

    override fun resetFeature(feature: Feature) {
        when (feature::class) {
            LoginFeature::class -> resetLogin()
        }
    }

    private fun initLogin() =
        LoginFeatureComponentHolder.init(
            dependencies = LoginFeatureComponentDependencies(
                coreComponent = CoreComponentHolder.get(),
                loginComponent = loginComponentHolder.init(
                    dependencies = LoginComponentDependencies(
                        coreComponent = CoreComponentHolder.get()
                    )
                )
            )
        )

    private fun resetLogin() {
        LoginFeatureComponentHolder.reset()
        loginComponentHolder.reset()
    }
}