package com.example.sainik.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sainik.EventData
import com.example.sainik.R

class MyEventListAdapter(private val currentUserPhoneNumber: String) : RecyclerView.Adapter<MyEventListAdapter.MyViewHolder>(){


    var myEventDataList= emptyList<EventData>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEventListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_events_row_layout,parent,false)
        return MyEventListAdapter.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myEventDataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.my_event_title_txt).text=myEventDataList[position].event_title
        holder.itemView.findViewById<TextView>(R.id.my_event_location_txt).text=myEventDataList[position].event_location
        holder.itemView.findViewById<ConstraintLayout>(R.id.my_event_row_background).setOnClickListener {
            val action = MyEventsFragmentDirections.actionMyEventsFragmentToEventStatisticsFragment(currentUserPhoneNumber,myEventDataList[position].event_title,myEventDataList[position].event_location)
            findNavController(holder.itemView).navigate(action)
        }

    }

    fun setData(eventData: List<EventData>){
        this.myEventDataList=eventData
        notifyDataSetChanged()
    }

}