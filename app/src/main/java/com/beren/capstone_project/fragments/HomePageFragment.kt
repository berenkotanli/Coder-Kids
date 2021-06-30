package com.beren.capstone_project.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        /*val layoutManager = LinearLayoutManager( getActivity())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        design.rvForyou.layoutManager=layoutManager*/


        viewModel.productsList.observe(viewLifecycleOwner, { productsList ->

             adapter = ProductsAdapter(requireContext(), productsList)
            // design.adapter = adapter
             design.adapter = adapter
         })
       // design.rvCourses.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        //mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)


      /*  var url="https://docs.google.com/uc?id=1phLSklIyFOClt_I-lAs1sJ1kMEKVA5c6"
        var id = design.foto
        Picasso.get().load(url).into(id)*/
        design.rvForyou.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)


        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: HomePageFragmentViewModel by viewModels()
        viewModel=temp
    }
   /* fun cardClicked(view:View){
        Navigation.findNavController(view).navigate(R.id.actionToDetails)
    }*/



}
