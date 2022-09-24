package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sainik.R
import com.example.sainik.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var view: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        view = FragmentLoginBinding.inflate(inflater,container,false)

        view.registerTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        view.loginBt.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_eventsFragment)
        }

        return view.root
    }


}