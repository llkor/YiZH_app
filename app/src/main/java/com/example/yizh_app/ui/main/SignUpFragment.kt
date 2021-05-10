package com.example.yizh_app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.yizh_app.databinding.FragmentSignUpBinding
import androidx.navigation.fragment.findNavController

import com.example.yizh_app.R


class SignUpFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentSignUpBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentSignUpFragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentSignUpFragmentBinding
        return fragmentSignUpFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            signupFragment = this@SignUpFragment
        }
    }

}