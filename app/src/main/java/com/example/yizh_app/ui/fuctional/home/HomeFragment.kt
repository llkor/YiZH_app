package com.example.yizh_app.ui.fuctional.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.yizh_app.R
import com.example.yizh_app.databinding.FragmentSignUpBinding
import com.example.yizh_app.databinding.HomeFragmentBinding
import com.example.yizh_app.ui.main.MainViewModel

class HomeFragment : Fragment() {

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private var binding: HomeFragmentBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentHomeBinding = HomeFragmentBinding.inflate(inflater, container, false)
        binding = fragmentHomeBinding
        return fragmentHomeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            homeFragment = this@HomeFragment
        }
    }

}