package com.beren.capstone_project.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beren.capstone_project.R
import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.UsersDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupTabFragment: Fragment() {
    private lateinit var udaoi: UsersDaoInterface
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val tasarim = inflater.inflate(R.layout.signup_tab_fragment, container, false)
         udaoi=ApiUtils.getUsersDaoInterface()
        //register()

        return tasarim
    }

    fun register(){
        udaoi.create_user("berenkotanli@gmail.com","Beren Kotanlı","beren123","5353201306")
                .enqueue(object: Callback<CRUDResponse>{
                    override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                        Log.e("Başarı",response.body().success.toString())
                        Log.e("Mesaj",response.body().message)
                    }

                    override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                        TODO("Not yet implemented")
                    }

                })
    }
}