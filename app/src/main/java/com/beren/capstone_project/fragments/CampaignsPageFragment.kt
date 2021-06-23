package com.beren.capstone_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.beren.capstone_project.R
import com.beren.capstone_project.databinding.FragmentCampaignsPageBinding

class CampaignsPageFragment : Fragment() {

    private lateinit var design: FragmentCampaignsPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_campaigns_page, container, false)
        return design.root
    }


}