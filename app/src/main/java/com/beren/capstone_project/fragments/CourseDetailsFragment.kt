package com.beren.capstone_project.fragments

import android.location.Location
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentCourseDetailsBinding
import com.squareup.picasso.Picasso

class CourseDetailsFragment : Fragment() {
    private lateinit var design: FragmentCourseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design =DataBindingUtil.inflate(inflater,R.layout.fragment_course_details, container, false)
        val mContext= activity

        val bundle: CourseDetailsFragmentArgs by navArgs()
        design.productObject=bundle.courseDetailsObject

         var url="https://docs.google.com/uc?id="+bundle.courseDetailsObject.urun_gorsel_url
         var id = design.coursePhoto
         Picasso.get().load(url).into(id)

        return design.root
    }
}
