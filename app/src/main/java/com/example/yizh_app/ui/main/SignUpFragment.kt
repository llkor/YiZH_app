package com.example.yizh_app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.yizh_app.databinding.FragmentSignUpBinding
import androidx.navigation.fragment.findNavController
import com.example.yizh_app.FunctionalActivity

import com.example.yizh_app.R
import com.example.yizh_app.ui.fuctional.profile.AboutUsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentSignUpBinding? = null

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUser: DatabaseReference
    private var firebaseUserID : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentSignUpFragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentSignUpFragmentBinding
        return fragmentSignUpFragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        register_button.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser() {
        val  username: String = username_register.text.toString()
        val  email: String = email_register.text.toString()
        val  password: String = password_register.text.toString()

        if(username ==  "")
        {
            Toast.makeText(activity,"username is needed", Toast.LENGTH_LONG).show()
        }
        else if(email == "")
        {
            Toast.makeText(activity,"email is needed", Toast.LENGTH_LONG).show()
        }
        else if(password == "")
        {
            Toast.makeText(activity,"password is needed", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful)
                    {
                        firebaseUserID = mAuth.currentUser!!.uid
                        refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["username"] = username
                        userHashMap["email"] = email

                        refUser.updateChildren(userHashMap)
                            .addOnCompleteListener{task ->
                                if (task.isSuccessful)
                                {
                                    val intent = Intent(activity, FunctionalActivity::class.java)
                                    intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                                    startActivity(intent)

                                }
                                
                            }
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
            signupFragment = this@SignUpFragment
        }
    }

}