package com.beren.capstone_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentProfilePageBinding
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.UsersDaoInterface


class ProfilePageFragment : Fragment() {
    private lateinit var design: FragmentProfilePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       design =DataBindingUtil.inflate(inflater,R.layout.fragment_profile_page, container, false)

        loadData()

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
 fun loadData(){
     val sharedPreferences= context?.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
     val savedName= sharedPreferences?.getString("STRING_NAME",null)
     val savedMail= sharedPreferences?.getString("STRING_MAIL",null)
     val savedPhone= sharedPreferences?.getString("STRING_PHONE",null)

     design.textViewUserNameSurname.text=savedName
     design.textViewUsermail.text=savedMail
     design.textViewUserphone.text=savedPhone

 }

}