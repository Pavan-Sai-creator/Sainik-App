package com.example.sainik.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sainik.R
import com.example.sainik.UserData
import com.example.sainik.UserViewModel
import com.example.sainik.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {


    private lateinit var view: FragmentRegistrationBinding
    private val mUserViewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        view = FragmentRegistrationBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        view.registerBt.setOnClickListener {
            insertDataToDb()
        }

        return view.root
    }

    private fun insertDataToDb() {
        //val mName = view.nameEt.text.toString()
        //val mEmail = view.emailForRegistrationEt.toString()
        val mPhone = view.phoneNoEt.text.toString()
        val mPassword = view.passwordEt.text.toString()

        val validation = verifyRegistrationDataFromUser(mPassword,mPhone)

        if(validation){
            // Insert data to database
            val newData = UserData(
                mPhone,
                mPassword
            )
            mUserViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    fun verifyRegistrationDataFromUser(password: String, phone: String): Boolean{
        return if( TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
            false
        } else !( phone.isEmpty() || password.isEmpty())
    }


}