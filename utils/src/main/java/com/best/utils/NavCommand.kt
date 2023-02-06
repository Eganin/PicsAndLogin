package com.best.utils

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavCommand(
    val target: NavCommands,
    val args: Bundle? = null,
    val navOptions: NavOptions?=null
)