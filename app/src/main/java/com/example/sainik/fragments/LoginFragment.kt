package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sainik.*
import com.example.sainik.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var view: FragmentLoginBinding
    lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        view = FragmentLoginBinding.inflate(inflater,container,false)

        mUserViewModel = ViewModelProvider(this, UserViewModelFactory(activity?.application as Application)).get(UserViewModel::class.java)


        view.registerTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        view.loginBt.setOnClickListener {
            mUserViewModel.getAllData.observe(viewLifecycleOwner, Observer {
                val phone: String = view.emailEt.text.toString()
                val password: String = view.passwordEt.text.toString()
                if(it.contains(UserData(phone,password))){
                    val action = LoginFragmentDirections.actionLoginFragmentToEventsFragment(phone)
                    findNavController().navigate(action)
                }else{
                    Toast.makeText(context,"Wrong Credentials",Toast.LENGTH_LONG).show()
                }
            })
        }

        return view.root
    }




}