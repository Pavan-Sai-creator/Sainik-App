package com.example.sainik.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sainik.R
import com.example.sainik.databinding.FragmentOrganizeEventBinding
import org.json.JSONException

import org.json.JSONObject





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
                    val url="https://sainikmain.herokuapp.com/predict"

                    val stringRequest: StringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener { response ->
                            val jsonObject = JSONObject(response)
                            var footfall = jsonObject.getString("footfall")
                            val action = OrganizeEventFragmentDirections.actionOrganizeEventFragmentToEventAnalysisFragment(mtitle,mlocation,mcapacity,mdescription,morgname,morgnumber,footfall)
                            findNavController().navigate(action)

                        },
                        { Log.d("API","Error in calling API") }
                    ) {
                        override fun getParams(): MutableMap<String, String> {
                            val hashMap = HashMap<String, String>()
                            val a=(0..4).random().toString()
                            val b=(0..100).random().toString()
                            val c=(0..5).random().toString()
                            val d=(0..3).random().toString()
                            val e=(0..4).random().toString()
                            val f=(0..4).random().toString()

                            hashMap.put("event", a)
                            hashMap.put("age", b)
                            hashMap.put("location", c)
                            hashMap.put("type", d)
                            hashMap.put("time", e)
                            hashMap.put("occupation", f)

                            return hashMap
                        }
                    }
                    val queue = Volley.newRequestQueue(requireContext())
                    queue.add(stringRequest)

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