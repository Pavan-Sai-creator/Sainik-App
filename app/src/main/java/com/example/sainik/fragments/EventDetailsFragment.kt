package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sainik.*
import com.example.sainik.databinding.FragmentEventDetailsBinding
import com.example.sainik.databinding.FragmentEventStatisticsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EventDetailsFragment : Fragment() {

    private lateinit var view: FragmentEventDetailsBinding
    private lateinit var mEventDetailsViewModel: EventDetailsViewModel

    private val args by navArgs<EventDetailsFragmentArgs>()
    private val currentUserPhoneNumber by lazy { args.currUserPhoneNumber }
    private val currEventTitle by lazy { args.currEventTitle }
    private val currEventLocation by lazy { args.currEventLocation }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view = FragmentEventDetailsBinding.inflate(inflater, container, false)

        mEventDetailsViewModel = ViewModelProvider(
            this, EventDetailsViewModelFactory(
                activity?.application as Application,
                currentUserPhoneNumber,
                currEventTitle,
                currEventLocation
            )
        ).get(
            EventDetailsViewModel::class.java
        )

        GlobalScope.launch(Dispatchers.IO) {
            setTextViews(currentUserPhoneNumber, currEventTitle, currEventLocation)
        }

        view.registerEventBtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                mEventDetailsViewModel.updateData(currentUserPhoneNumber, currEventTitle, currEventLocation)
                val action =
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToEventsFragment(
                        currentUserPhoneNumber
                    )
                findNavController().navigate(action)
            }


        }
        return view.root
    }

        private suspend fun setTextViews(a:String,b:String,c:String){
            view.EventdetailsMyEventTitleTv.text = mEventDetailsViewModel.repository.getCurrentEventData(a,b,c).event_title
            view.EventdetailsMyEventLocationTv.text = mEventDetailsViewModel.repository.getCurrentEventData(a,b,c).event_location
            view.EventdetailsMyEventDescriptionTv.text = mEventDetailsViewModel.repository.getCurrentEventData(a,b,c).event_description
            view.EventDetailsOrgnoTv.text = mEventDetailsViewModel.repository.getCurrentEventData(a,b,c).organiser_number
        }

}