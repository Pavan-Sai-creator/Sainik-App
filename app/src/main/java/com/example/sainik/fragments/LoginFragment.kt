package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sainik.MyDatabase
import com.example.sainik.R
import com.example.sainik.UserData
import com.example.sainik.UserViewModel
import com.example.sainik.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var view: FragmentLoginBinding
    private val mUserViewModel: UserViewModel by viewModels()
   // private var loginStatus: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        if(loginStatus == true){
//            findNavController().navigate(R.id.action_loginFragment_to_eventsFragment)
//        }
        // Inflate the layout for this fragment
        view = FragmentLoginBinding.inflate(inflater,container,false)

        view.registerTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        view.loginBt.setOnClickListener {
            mUserViewModel.getAllData.observe(viewLifecycleOwner, Observer {
                val phone: String = view.emailEt.text.toString()
                val password: String = view.passwordEt.text.toString()
                if(it.contains(UserData(phone,password))){
                    // loginStatus = true
                    findNavController().navigate(R.id.action_loginFragment_to_eventsFragment)
                }else{
                    Toast.makeText(context,"Wrong Credentials",Toast.LENGTH_LONG).show()
                }
            })
        }

        return view.root
    }



}