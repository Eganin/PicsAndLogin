package com.best.login_feature

import android.net.Uri
import com.best.core.Feature
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginFeature(
    override val isEnabled: Boolean,
    @IgnoredOnParcel
    override val name: String = NAME,
    @IgnoredOnParcel
    override val requiresAuthorization: Boolean = false
) : Feature {

    override fun canHandleDeeplink(deeplink: Uri): Boolean {
        return when (deeplink.path) {
            LOGIN_DEEPLINK -> true
            else -> false
        }
    }

    companion object {
        internal const val NAME = "Login"
        const val LOGIN_DEEPLINK = "/login"
    }
}