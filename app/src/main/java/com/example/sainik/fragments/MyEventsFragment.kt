package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sainik.*
import com.example.sainik.databinding.FragmentMyEventsBinding


class MyEventsFragment : Fragment() {

    lateinit var mMyEventViewModel: MyEventViewModel
    private val args by navArgs<MyEventsFragmentArgs>()
    private val adapter:MyEventListAdapter by lazy { MyEventListAdapter() }

    private val currentUserPhoneNumber by lazy {
        args.currentUserPhoneNumber
    }

    private lateinit var view:  FragmentMyEventsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentMyEventsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        Toast.makeText(requireContext(),"Current Number is ${currentUserPhoneNumber}",Toast.LENGTH_SHORT).show()
        mMyEventViewModel = ViewModelProvider(this, MyEventViewModelFactory(activity?.application as Application,currentUserPhoneNumber)).get(MyEventViewModel::class.java)


        val recyclerView=view.myEventRecyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireActivity())

        mMyEventViewModel.getMyEventData.observe(viewLifecycleOwner, Observer { myEventdata -> adapter.setData(myEventdata) })

        return view.root
    }


}