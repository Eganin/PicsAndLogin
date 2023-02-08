package com.example.picsangloginapp.data

import com.best.core.Feature
import com.best.core.di.CoreComponentHolder
import com.best.login_api.di.LoginComponentDependencies
import com.best.login_feature.models.LoginFeature
import com.best.login_feature.di.LoginFeatureComponentDependencies
import com.best.login_feature.di.LoginFeatureComponentHolder
import com.best.login_provider.loginComponentHolder
import com.example.pics_api.di.PicComponentDependencies
import com.example.pics_feature.models.PicsFeature
import com.example.pics_feature.di.PicsFeatureComponentDependencies
import com.example.pics_feature.di.PicsFeatureComponentHolder
import com.example.pics_provider.picsComponentHolder
import com.example.picsangloginapp.domain.RootInteractor

class RootInteractorImpl : RootInteractor {
    override fun initFeature(feature: Feature) {
        when (feature::class) {
            LoginFeature::class -> initLogin()
            PicsFeature::class -> initPics()
        }
    }

    override fun resetFeature(feature: Feature) {
        when (feature::class) {
            LoginFeature::class -> resetLogin()
            PicsFeature::class -> resetPics()
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

    private fun initPics() {
        PicsFeatureComponentHolder.init(
            dependencies = PicsFeatureComponentDependencies(
                coreComponent = CoreComponentHolder.get(),
                picsComponent = picsComponentHolder.init(
                    dependencies = PicComponentDependencies(
                        coreComponent = CoreComponentHolder.get()
                    )
                )
            )
        )
    }

    private fun resetPics() {
        PicsFeatureComponentHolder.reset()
        picsComponentHolder.reset()
    }
}