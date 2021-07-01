package com.beren.capstone_project.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beren.capstone_project.R
import com.beren.capstone_project.adapters.ProductsAdapter
import com.beren.capstone_project.databinding.FragmentHomePageBinding
import com.beren.capstone_project.viewmodel.HomePageFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home_page.view.*

class HomePageFragment : Fragment() {

    private lateinit var design: FragmentHomePageBinding
    private lateinit var adapter:ProductsAdapter
    private lateinit var viewModel: HomePageFragmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

         design = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        design.toolbarTitle="CODER KIDS"
        (activity as AppCompatActivity).setSupportActionBar(design.toolbarHome)


        viewModel.productsList.observe(viewLifecycleOwner, { productsList ->
             adapter = ProductsAdapter(requireContext(), productsList)
             design.adapter = adapter
         })

        design.rvForyou.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: HomePageFragmentViewModel by viewModels()
        viewModel=temp
    }

}
