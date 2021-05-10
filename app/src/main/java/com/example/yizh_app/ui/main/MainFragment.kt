package com.example.yizh_app.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.yizh_app.FunctionalActivity
import com.example.yizh_app.R
import com.example.yizh_app.databinding.MainFragmentBinding
import kotlin.reflect.KClass

class MainFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null



    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_signUpFragment2)
        }
        view.findViewById<Button>(R.id.nextActivity).setOnClickListener{
            val intent = Intent(activity,FunctionalActivity::class.java)
            startActivity(intent)
        }

    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            mainFragment = this@MainFragment
        }
    }



}