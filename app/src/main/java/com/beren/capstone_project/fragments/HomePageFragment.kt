package com.beren.capstone_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beren.capstone_project.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val design = inflater.inflate(R.layout.fragment_home_page, container, false)
        var url="https://docs.google.com/uc?id=1phLSklIyFOClt_I-lAs1sJ1kMEKVA5c6"
        var id = design.foto
        Picasso.get().load(url).into(id)


        return design
    }


    }
