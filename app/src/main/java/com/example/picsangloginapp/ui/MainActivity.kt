package com.example.picsangloginapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.best.utils.NavCommand
import com.best.utils.NavCommands
import com.best.utils.NavigationProvider
import com.example.picsangloginapp.R
import com.example.picsangloginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationProvider {

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                uri = target.uri,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is NavCommands.Browser -> openBrowser(url = target.url)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent = intent)
    }

    private fun openBrowser(url: String) {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { setPackage("com.android.chrome") }
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }

    private fun openDeepLink(uri: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setEnterAnim(com.best.utils.R.anim.slide_in_up_enter)
                .setExitAnim(com.best.utils.R.anim.slide_out_up_enter)
                .setPopEnterAnim(com.best.utils.R.anim.slide_in_up_exit)
                .setPopExitAnim(com.best.utils.R.anim.slide_out_up_exit)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(
                    if (isSingleTop) R.id.nav_graph_application else -1,
                    inclusive = isSingleTop
                )
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(com.best.utils.R.anim.slide_in_left)
                .setExitAnim(com.best.utils.R.anim.slide_out_left)
                .setPopEnterAnim(com.best.utils.R.anim.slide_in_left)
                .setPopExitAnim(com.best.utils.R.anim.slide_out_right)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(
                    if (isSingleTop) R.id.nav_graph_application else -1,
                    inclusive = isSingleTop
                )
                .build()
        }

        navController.navigate(uri, navOptions)
    }
}