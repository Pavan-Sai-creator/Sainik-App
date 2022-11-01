package com.example.sainik.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sainik.EventData
import com.example.sainik.R

class EventListAdapter(private val impvariable: String): RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {
    var eventDataList= emptyList<EventData>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_row_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventDataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.event_title_txt).text=eventDataList[position].event_title
        holder.itemView.findViewById<TextView>(R.id.event_location_txt).text=eventDataList[position].event_location
        holder.itemView.findViewById<ConstraintLayout>(R.id.event_row_background).setOnClickListener {
            val action = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(eventDataList[position].event_title,eventDataList[position].event_location,eventDataList[position].organiser_number,impvariable)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(eventData: List<EventData>){
        this.eventDataList=eventData
        notifyDataSetChanged()
    }


}