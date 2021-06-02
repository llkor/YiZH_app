package com.example.yizh_app.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.yizh_app.FunctionalActivity
import com.example.yizh_app.R
import com.example.yizh_app.databinding.MainFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.reflect.KClass

class MainFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null

    private lateinit var mAuth: FirebaseAuth



    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.go_sign_up_button).setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_signUpFragment2)
        }

        mAuth = FirebaseAuth.getInstance()
        sign_in_button.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser() {
        val  email: String = email_log_in.text.toString()
        val  password: String = password_log_in.text.toString()

        if(password ==  "")
        {
            Toast.makeText(activity,"password is needed", Toast.LENGTH_LONG).show()
        }
        else if(email == "")
        {
            Toast.makeText(activity,"email is needed", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful)
                    {
                        val intent = Intent(activity, FunctionalActivity::class.java)
                        intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(activity,"Error massage: " + task.exception?.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
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