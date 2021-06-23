package com.beren.capstone_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentCourseDetailsBinding

class CourseDetailsFragment : Fragment() {
    private lateinit var design: FragmentCourseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design =DataBindingUtil.inflate(inflater,R.layout.fragment_course_details, container, false)

        val bundle: CourseDetailsFragmentArgs by navArgs()
        design.productObject=bundle.courseDetailsObject
        return design.root
    }

}