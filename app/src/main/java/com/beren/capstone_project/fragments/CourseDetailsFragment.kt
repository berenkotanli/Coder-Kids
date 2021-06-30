package com.beren.capstone_project.fragments


import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentCourseDetailsBinding
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.beren.capstone_project.viewmodel.CourseDetailsFragmentViewModel

import com.squareup.picasso.Picasso

class CourseDetailsFragment : Fragment() {
    private lateinit var design: FragmentCourseDetailsBinding
    private lateinit var viewModel: CourseDetailsFragmentViewModel
    private lateinit var pdaoi:ProductsDaoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design =DataBindingUtil.inflate(inflater,R.layout.fragment_course_details, container, false)
        val mContext= activity
        pdaoi=ApiUtils.getProductsDaoInterface()
        design.detailsFragment=this
        val bundle: CourseDetailsFragmentArgs by navArgs()
        design.productObject=bundle.courseDetailsObject

         var url="https://docs.google.com/uc?id="+bundle.courseDetailsObject.urun_gorsel_url
         var id = design.coursePhoto
         Picasso.get().load(url).into(id)

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: CourseDetailsFragmentViewModel by viewModels()
        viewModel=temp
    }
    fun addToCart(id:Int,cartStatus:Int){
        viewModel.updateCart(id,cartStatus)
       viewModel.success.observe(viewLifecycleOwner,{
            println(it)
            if (it == 1) {
                Toast.makeText(requireContext(),"Sepete eklendi",Toast.LENGTH_LONG).show()
                Log.e("55","eklendi herhalde")
            } else if (it == 0) {
                Toast.makeText(requireContext(), "Giriş Yapılamadı!", Toast.LENGTH_SHORT).show()
            }
        })

}}
