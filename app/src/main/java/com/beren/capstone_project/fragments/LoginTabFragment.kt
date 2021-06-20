package com.beren.capstone_project.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beren.capstone_project.R
import com.beren.capstone_project.entity.UsersResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginTabFragment : Fragment(){
    private lateinit var udaoi: UsersDaoInterface
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val design=inflater.inflate(R.layout.login_tab_fragment, container, false)
        udaoi=ApiUtils.getUsersDaoInterface()
       // login()
        return design
    }
    fun login(){
        udaoi.login("berenkotanli@gmail.com","beren123").enqueue(object : Callback<UsersResponse>{
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

}