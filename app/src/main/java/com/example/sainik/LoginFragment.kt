package com.example.sainik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sainik.R
import com.example.sainik.databinding.FragmentLoginBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class LoginFragment : Fragment() {

    private lateinit var view: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        view = FragmentLoginBinding.inflate(inflater,container,false)


        return view.root
    }


}