package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sainik.*
import com.example.sainik.databinding.FragmentEventAnalysisBinding
import com.example.sainik.databinding.FragmentEventsBinding


class EventAnalysisFragment : Fragment() {

    private lateinit var view: FragmentEventAnalysisBinding
    lateinit var mEventViewModel: EventViewModel 
    private val args by navArgs<EventAnalysisFragmentArgs>()
    val footfall by lazy {
        args.footfall
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentEventAnalysisBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        view.footfallTv.text=footfall
        mEventViewModel = ViewModelProvider(this, EventViewModelFactory(activity?.application as Application/*,"111"*/)).get(EventViewModel::class.java)

        view.editEventBtn.setOnClickListener {
            findNavController().navigate(R.id.action_eventAnalysisFragment_to_organizeEventFragment)
        }

        view.launchEventBtn.setOnClickListener {

            insertEventDataToDb()
            val action = EventAnalysisFragmentDirections.actionEventAnalysisFragmentToEventsFragment(args.organizerNumber)
            findNavController().navigate(action)
        }

        return view.root
    }

    private fun insertEventDataToDb() {

            // Insert data to database
            val newData = EventData(
                0,
                args.eventTitle,
                args.eventLocation,
                args.eventCapacity,
                args.eventDescription,
                args.organizerName,
                args.organizerNumber,
                args.numberOfParticipants
            )
            mEventViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_SHORT).show()
        }
    }
