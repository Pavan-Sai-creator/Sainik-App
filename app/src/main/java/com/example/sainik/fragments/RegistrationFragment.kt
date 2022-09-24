package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sainik.R
import com.example.sainik.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    private lateinit var view: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        view = FragmentRegistrationBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return view.root
    }


}