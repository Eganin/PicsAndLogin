package com.best.utils

import androidx.fragment.app.Fragment

fun Fragment.navigate(navCommand: NavCommand) =
    (requireActivity() as? NavigationProvider)?.launch(navCommand = navCommand)