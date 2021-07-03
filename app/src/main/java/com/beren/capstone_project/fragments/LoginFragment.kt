package com.beren.capstone_project.fragments

import android.content.Context
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
import com.beren.capstone_project.adapters.CartItemsAdapter
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: LoginFragmentViewModel by viewModels()
        viewModel=temp
        //loadData()
    }
    fun buttonLogin(user_email:String, user_password:String){
        viewModel.login(user_email,user_password)
        viewModel.success.observe(viewLifecycleOwner, {
            println(it)
            if (it == 1) {
                val intent = Intent(getActivity(), MainActivity::class.java)
                getActivity()?.startActivity(intent)
                viewModel.user.observe(viewLifecycleOwner, { userInfo ->
                   // Log.e("7",userInfo.toString())

                    val sharedPreferences= context?.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
                    val editor= sharedPreferences?.edit()
                    editor?.apply{
                        for (i in userInfo){
                            putString("STRING_NAME",i.ad_soyad)
                            putString("STRING_MAIL",i.mail_adres)
                            putString("STRING_PHONE",i.telefon)
                        }
                    }?.apply()
                    Toast.makeText(requireContext(),"Giriş başarılı",Toast.LENGTH_LONG).show()

                })
            } else if (it == 0) {
                Toast.makeText(requireContext(), "Şifre veya e-posta hatalı", Toast.LENGTH_SHORT).show()
            }

        })

       // LoginFragmentDirections.actionToProfile()


        //Navigation.findNavController(view).navigate(R.id.transitionHomePageFragment)
    }

    }
