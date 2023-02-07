package com.example.pics_feature

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
    companion object {
        private const val NAME = "Pictures"
    }

}
