package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sainik.EventViewModel
import com.example.sainik.EventViewModelFactory
import com.example.sainik.R
import com.example.sainik.databinding.FragmentEventsBinding


open class EventsFragment : Fragment(){
    private val adapter:EventListAdapter by lazy { EventListAdapter() }
    private val args by navArgs<EventsFragmentArgs>()

   lateinit var mEventViewModel: EventViewModel

   val currentUserPhoneNumber by lazy {
        args.currentUserPhoneNumber
    }

    private lateinit var view: FragmentEventsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        // Inflate the layout for this fragment
        view = FragmentEventsBinding.inflate(inflater,container,false)

        mEventViewModel = ViewModelProvider(this, EventViewModelFactory(activity?.application as Application/*,currentUserPhoneNumber*/)).get(EventViewModel::class.java)


        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireActivity())

        mEventViewModel.getAllEventData.observe(viewLifecycleOwner, Observer { eventdata -> adapter.setData(eventdata) })

        view.addFab.setOnClickListener {
            findNavController().navigate(EventsFragmentDirections.actionEventsFragmentToOrganizeEventFragment(currentUserPhoneNumber))
        }
        setHasOptionsMenu(true)

        return view.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.events_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_my_events)
        findNavController().navigate(EventsFragmentDirections.actionEventsFragmentToMyEventsFragment(currentUserPhoneNumber))
        return super.onOptionsItemSelected(item)
    }
}