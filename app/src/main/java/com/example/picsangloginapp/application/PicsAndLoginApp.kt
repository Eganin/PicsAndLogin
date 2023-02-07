package com.example.picsangloginapp.application

import android.app.Application
import com.best.core.di.CoreComponentDependencies
import com.best.core.di.CoreComponentHolder
import com.example.picsangloginapp.di.RootComponentDependencies
import com.example.picsangloginapp.di.RootComponentHolder

class PicsAndLoginApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RootComponentHolder.init(
            dependencies = RootComponentDependencies(
                coreComponent = CoreComponentHolder.init(
                    dependencies = CoreComponentDependencies(application = this)
                )
            )
        )
    }
}