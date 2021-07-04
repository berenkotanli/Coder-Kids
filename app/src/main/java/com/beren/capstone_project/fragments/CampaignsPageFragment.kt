package com.beren.capstone_project.fragments

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beren.capstone_project.MainActivity
import com.beren.capstone_project.R
import com.beren.capstone_project.adapters.CampaignsItemAdapter
import com.beren.capstone_project.databinding.FragmentCampaignsPageBinding
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.beren.capstone_project.viewmodel.CampaignsPageFragmentViewModel
import com.beren.capstone_project.viewmodel.CartPageFragmentViewModel
import kotlinx.android.synthetic.main.fragment_campaigns_page.*

class CampaignsPageFragment : Fragment() {

    private lateinit var design: FragmentCampaignsPageBinding
    private lateinit var viewModel: CampaignsPageFragmentViewModel
    private lateinit var pdaoi: ProductsDaoInterface
    private lateinit var adapter: CampaignsItemAdapter

   /* private lateinit var actionBar:ActionBar
    private lateinit var myModelList:ArrayList<MyModel>
    private lateinit var myAdapter: MyAdapter*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_campaigns_page, container, false)

        pdaoi= ApiUtils.getProductsDaoInterface()
        design.campaignsPageFragment=this


       design.toolbarProm.setNavigationOnClickListener {
            val myIntent = Intent(activity, MainActivity::class.java)
            startActivity(myIntent)
        }

        viewModel.campaignsItemList.observe(viewLifecycleOwner, { campaignsItemList ->
            adapter = CampaignsItemAdapter(requireContext(), campaignsItemList,viewModel)
            design.discountadapter = adapter
        })
        design.rvitempro.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: CampaignsPageFragmentViewModel by viewModels()
        viewModel=temp
        viewModel.updateDiscount()

       /* loadCards()
        viewpagerpro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val title=myModelList[position].title }
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })*/

    }
  /*  private fun loadCards() {
        myModelList= ArrayList()
        myModelList.add(
            MyModel(
                "Scratch Eğitimi",
                "asdvgfkjn",
                "15/07/2021",
                "R.drawable.arkaplan"))
        myModelList.add(MyModel("Mbot Eğitimi","asdvgfkjn","15/07/2021","R.drawable.arkaplan"))
        myModelList.add(MyModel("Arduino Eğitimi","asdvgfkjn","15/07/2021","R.drawable.arkaplan"))


        myAdapter= MyAdapter(requireContext(),myModelList)

        viewpagerpro.adapter=myAdapter
        viewpagerpro.setPadding(100,0,100,0)
    }*/

    fun updateDiscount(){
        viewModel.updateDiscount()
        viewModel.success.observe(viewLifecycleOwner,{
            println(it)
            if (it == 1) {
                Log.e("57","kampanya uygulandı")
            } else if (it == 0) {
                Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
            }
        })

    }

}