package com.best.core

import android.net.Uri
import android.os.Parcelable

interface Feature : Parcelable {
    val name: String
    val isEnabled: Boolean
    val requiresAuthorization: Boolean

    fun canHandleDeeplink(deeplink: Uri): Boolean
}