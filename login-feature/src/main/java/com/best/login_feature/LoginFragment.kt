package com.best.login_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.best.core.other.listenChanges
import com.best.core.other.load
import com.example.picsangloginapp.databinding.FragmentLayoutBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLayoutBinding? = null

    private val binding get() = _binding!!

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        binding.progressBar.bringToFront()
        binding.logoImageView.load(getString(R.string.logo_url))

        viewModel.observe(owner = viewLifecycleOwner){
            it.handle(binding=binding)
        }

        binding.loginButton.setOnClickListener {
            viewModel.login(
                email = binding.emailAddressEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        binding.emailAddressEditText.listenChanges {
            binding.emailAddressTextInputLayout.show(show = false, message = "")
        }

        binding.passwordEditText.listenChanges {
            binding.passwordTextInputLayout.show(show = false, message = "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}