package com.example.sainik.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sainik.R
import com.example.sainik.databinding.FragmentEventsBinding


class EventsFragment : Fragment() {

    private lateinit var view: FragmentEventsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        view = FragmentEventsBinding.inflate(inflater,container,false)

        view.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_eventsFragment_to_organizeEventFragment)
        }
        setHasOptionsMenu(true)

        return view.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.events_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_my_events)
        findNavController().navigate(R.id.action_eventsFragment_to_myEventsFragment)
        return super.onOptionsItemSelected(item)
    }
}