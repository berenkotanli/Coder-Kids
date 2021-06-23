package com.beren.capstone_project.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentLoginBinding
import com.beren.capstone_project.entity.UsersResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.UsersDaoInterface
import com.beren.capstone_project.viewmodel.LoginFragmentViewModel
import com.beren.capstone_project.viewmodel.SignupFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
  private lateinit var  design: FragmentLoginBinding
    private lateinit var udaoi: UsersDaoInterface
    private lateinit var viewModel: LoginFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false )
        udaoi= ApiUtils.getUsersDaoInterface()
        design.userLoginFragment=this
        // login()
        return design.root
    }
    fun login(){
        udaoi.login("berenkotanli@gmail.com","beren123").enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>?, response: Response<UsersResponse>) {
                val user=response.body().users
                for(i in user){
                    if(i.deger==1){
                        Log.e("*********","*******")
                        Log.e("1",i.ad_soyad)
                        Log.e("2",i.mail_adres)
                        Log.e("3",i.telefon)
                        Log.e("4",i.sifre)
                        Log.e("5",i.deger.toString())
                    }else if(i.deger==0){
                        Log.e("1","giriş yapılamadı")
                    }
                }

            }

            override fun onFailure(call: Call<UsersResponse>?, t: Throwable?) {
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: LoginFragmentViewModel by viewModels()
        viewModel=temp
    }
    fun buttonLogin(user_email:String, user_password:String){
        viewModel.login(user_email,user_password)
    }

    }
