package com.example.pics_feature

import android.net.Uri
import com.best.core.Feature
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PicsFeature(
    override val isEnabled: Boolean,
    @IgnoredOnParcel
    override val name: String = NAME,
    @IgnoredOnParcel
    override val requiresAuthorization: Boolean = false
) : Feature {
    override fun canHandleDeeplink(deeplink: Uri): Boolean {
        return when (deeplink.path) {
            PICS_DEEPLINK -> true
            else -> false
        }
    }

    companion object {
        internal const val NAME = "Pictures"
        const val PICS_DEEPLINK = "/pics"
    }

}
