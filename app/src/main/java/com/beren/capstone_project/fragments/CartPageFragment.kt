package com.beren.capstone_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentCartPageBinding


class CartPageFragment : Fragment() {
    private lateinit var design: FragmentCartPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_cart_page, container, false)
        return design.root
    }


}