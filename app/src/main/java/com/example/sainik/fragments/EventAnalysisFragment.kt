package com.example.sainik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sainik.R
import com.example.sainik.databinding.FragmentEventAnalysisBinding
import com.example.sainik.databinding.FragmentEventsBinding


class EventAnalysisFragment : Fragment() {

    private lateinit var view: FragmentEventAnalysisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentEventAnalysisBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        view.editEventBtn.setOnClickListener {
            findNavController().navigate(R.id.action_eventAnalysisFragment_to_organizeEventFragment)
        }

        view.launchEventBtn.setOnClickListener {
            findNavController().navigate(R.id.action_eventAnalysisFragment_to_myEventsFragment)
        }

        return view.root
    }


}