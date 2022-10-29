package com.example.sainik.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sainik.EventData
import com.example.sainik.R

class MyEventListAdapter : RecyclerView.Adapter<MyEventListAdapter.MyViewHolder>(){

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

    }

    fun setData(eventData: List<EventData>){
        this.myEventDataList=eventData
        notifyDataSetChanged()
    }

}