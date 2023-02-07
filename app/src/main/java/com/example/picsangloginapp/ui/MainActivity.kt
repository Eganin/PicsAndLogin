package com.example.picsangloginapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.best.core.di.viewmodel.VmFactoryWrapper
import com.best.utils.NavCommand
import com.best.utils.NavCommands
import com.best.utils.NavigationProvider
import com.example.picsangloginapp.R
import com.example.picsangloginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationProvider {

    private lateinit var binding: ActivityMainBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val picsFragment = if (savedInstanceState == null) PicsFragment()
//        else supportFragmentManager.findFragmentByTag(PICS_FRAGMENT_TAG)!!
//
//        val loginFragment = if (savedInstanceState == null) LoginFragment()
//        else supportFragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG)!!
//
//        if (savedInstanceState == null)
//            supportFragmentManager.beginTransaction().apply {
//                add(R.id.container, loginFragment, LOGIN_FRAGMENT_TAG)
//                hide(loginFragment)
//                add(R.id.container, picsFragment, PICS_FRAGMENT_TAG)
//                commit()
//            }
//        else
//            binding.navView.selectedItemId =
//                if (savedInstanceState.getBoolean(IS_LOGIN_SELECTED_KEY)) R.id.navigation_login
//                else R.id.navigation_pics
//
//        binding.navView.setOnItemSelectedListener {
//            val showPics = it.itemId == R.id.navigation_pics
//            supportFragmentManager.beginTransaction().apply {
//                hide(if (showPics) loginFragment else picsFragment)
//                show(if (showPics) picsFragment else loginFragment)
//                commit()
//            }
//            true
//        }
//    }
//
//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState.putBoolean(
//            IS_LOGIN_SELECTED_KEY,
//            binding.navView.selectedItemId == R.id.navigation_login
//        )
//    }

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setupWithNavController(navController)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                uri = target.uri,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            else -> {}
            //is NavCommands.Browser -> openBrowser(url = target.url)
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

    private companion object {
        const val PICS_FRAGMENT_TAG = "picsFragment"
        const val LOGIN_FRAGMENT_TAG = "loginFragment"
        const val IS_LOGIN_SELECTED_KEY = "isLoginSelected"
    }
}