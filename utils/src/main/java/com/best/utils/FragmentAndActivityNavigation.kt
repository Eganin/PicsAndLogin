package com.best.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.navigate(navCommand: NavCommand) =
    (requireActivity() as? NavigationProvider)?.launch(navCommand = navCommand)

fun AppCompatActivity.navigate(navCommand: NavCommand)=
    (this as? NavigationProvider)?.launch(navCommand=navCommand)