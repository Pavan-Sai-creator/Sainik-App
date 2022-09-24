package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sainik.R
import com.example.sainik.databinding.FragmentMyEventsBinding


class MyEventsFragment : Fragment() {

    private lateinit var view:  FragmentMyEventsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentMyEventsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return view.root
    }


}