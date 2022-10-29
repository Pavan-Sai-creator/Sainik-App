package com.example.sainik.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sainik.R
import com.example.sainik.databinding.FragmentOrganizeEventBinding


class OrganizeEventFragment : Fragment() {

    private val args by navArgs<OrganizeEventFragmentArgs>()
    val currentUserPhoneNumber by lazy {
        args.currentUserPhoneNumber
    }

    private lateinit var view: FragmentOrganizeEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentOrganizeEventBinding.inflate(inflater,container,false)

        view.createEventBtn.setOnClickListener {
            val mtitle = view.eventTitleEt.text.toString()
            val mlocation = view.locationEt.text.toString()
            val mcapacity = view.eventCapacityEt.text.toString()
            val mdescription = view.eventDescriptionEt.text.toString()
            val morgname = view.organizerName.text.toString()
            val morgnumber = view.organizerNumber.text.toString()

            val phonNumberVerified = verifyPhoneNumber(morgnumber)
            if(phonNumberVerified){
                val verified = verifyEventDataFromUser(mtitle,mlocation,mcapacity,mdescription,morgname,morgnumber)
                if(verified) {
                    Toast.makeText(context,mdescription,Toast.LENGTH_LONG).show()
                    val action = OrganizeEventFragmentDirections.actionOrganizeEventFragmentToEventAnalysisFragment(mtitle,mlocation,mcapacity,mdescription,morgname,morgnumber)
                    findNavController().navigate(action)
                }
                else
                    Toast.makeText(context,"Fill all details",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Enter the number used to login",Toast.LENGTH_LONG).show()
            }

        }

        return view.root
    }

    fun verifyEventDataFromUser(title: String, location: String,cap: String, des: String, orgname:String, orgNum:String): Boolean{
        return if( TextUtils.isEmpty(title) || TextUtils.isEmpty(location)||TextUtils.isEmpty(cap) || TextUtils.isEmpty(des)||TextUtils.isEmpty(orgname) || TextUtils.isEmpty(orgNum)){
            false
        } else !( title.isEmpty() || location.isEmpty() || cap.isEmpty() || des.isEmpty() ||  orgname.isEmpty() || orgNum.isEmpty())
    }

    fun verifyPhoneNumber(organizerNumber: String): Boolean{
        if(organizerNumber==currentUserPhoneNumber){
            return true
        }
        return false
    }


}