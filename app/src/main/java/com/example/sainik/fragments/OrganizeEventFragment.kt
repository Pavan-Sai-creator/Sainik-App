package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sainik.R
import com.example.sainik.databinding.FragmentOrganizeEventBinding


class OrganizeEventFragment : Fragment() {

    private lateinit var view: FragmentOrganizeEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentOrganizeEventBinding.inflate(inflater,container,false)

        view.createEventBtn.setOnClickListener {
            findNavController().navigate(R.id.action_organizeEventFragment_to_eventAnalysisFragment)
        }

        return view.root
    }


}