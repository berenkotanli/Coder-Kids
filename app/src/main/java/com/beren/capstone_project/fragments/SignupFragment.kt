package com.beren.capstone_project.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.beren.capstone_project.MainActivity
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentSignupBinding
import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.UsersDaoInterface
import com.beren.capstone_project.viewmodel.SignupFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {

    private lateinit var design: FragmentSignupBinding
    private lateinit var udaoi: UsersDaoInterface
    private lateinit var viewModel: SignupFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        design= DataBindingUtil.inflate(inflater,R.layout.fragment_signup, container, false)
        udaoi= ApiUtils.getUsersDaoInterface()
        //register()
        design.userSignupFragment=this

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: SignupFragmentViewModel by viewModels()
        viewModel=temp
    }
    fun buttonRegister(user_name_surname:String, user_phone_number:String,user_email:String, user_password:String){
        viewModel.register(user_name_surname,user_email,user_phone_number,user_password)
        viewModel.success.observe(viewLifecycleOwner,{
            if (it == 1) {
                Toast.makeText(requireContext(),"Kayıt başarılı", Toast.LENGTH_LONG).show()

            }
        })

    }



}