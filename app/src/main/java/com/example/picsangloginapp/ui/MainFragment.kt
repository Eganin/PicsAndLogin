package com.example.picsangloginapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.best.login_feature.models.LoginFeature
import com.example.pics_feature.models.PicsFeature
import com.example.picsangloginapp.R
import com.example.picsangloginapp.databinding.FragmentMainBinding
import com.example.picsangloginapp.di.RootComponentHolder
import com.example.picsangloginapp.domain.RootInteractor
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var rootInteractor: RootInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RootComponentHolder.get().inject(mainFragment = this)
        rootInteractor.initFeature(feature = LoginFeature(isEnabled = true))
        rootInteractor.initFeature(feature = PicsFeature(isEnabled = true))
        val navController =
            (childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment).navController
        binding.mainBottomNavigationView.setupWithNavController(navController)
    }
}