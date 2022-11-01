package com.example.sainik.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sainik.*
import com.example.sainik.databinding.FragmentEventStatisticsBinding
import com.example.sainik.databinding.FragmentMyEventsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventStatisticsFragment : Fragment() {

    private lateinit var view:  FragmentEventStatisticsBinding
    private lateinit var mEventStatisticsViewModel: EventStatisticsFragmentViewModel

    private val args by navArgs<EventStatisticsFragmentArgs>()
    private val currentUserPhoneNumber by lazy { args.currUserPhoneNumber }
    private val currEventTitle by lazy { args.currEventTitle }
    private val currEventLocation by lazy { args.currEventLocation }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view = FragmentEventStatisticsBinding.inflate(inflater,container,false)

        mEventStatisticsViewModel = ViewModelProvider(this, EventStatisticsFragmentViewModelFactory(activity?.application as Application,
            currentUserPhoneNumber,
            currEventTitle,
            currEventLocation)).get(
            EventStatisticsFragmentViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            setTextViews(currentUserPhoneNumber, currEventTitle, currEventLocation)
        }

        view.cancelEventBtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                mEventStatisticsViewModel.cancelEvent()
                val action = EventStatisticsFragmentDirections.actionEventStatisticsFragmentToMyEventsFragment(currentUserPhoneNumber)
                findNavController().navigate(action)
            }

        }



        return view.root

    }
    private suspend fun setTextViews(a:String,b:String,c:String){
        view.statisticsMyEventTitleTv.text = mEventStatisticsViewModel.repository.getCurrentEventData(a,b,c).event_title
        view.statisticsMyEventLocationTv.text = mEventStatisticsViewModel.repository.getCurrentEventData(a,b,c).event_location
        view.statisticsMyEventDescriptionTv.text = mEventStatisticsViewModel.repository.getCurrentEventData(a,b,c).event_description
        view.statisticsMyEventNumberOfParticipantsTv.text = mEventStatisticsViewModel.repository.getCurrentEventData(a,b,c).number_of_participants.toString()
    }
}