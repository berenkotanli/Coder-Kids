package com.beren.capstone_project.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beren.capstone_project.R
import com.beren.capstone_project.SwipeGesture
import com.beren.capstone_project.adapters.CartItemsAdapter
import com.beren.capstone_project.databinding.FragmentCartPageBinding
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.beren.capstone_project.viewmodel.CartPageFragmentViewModel
import kotlinx.android.synthetic.main.fragment_cart_page.*


class CartPageFragment() : Fragment() {
    private lateinit var design: FragmentCartPageBinding
    private lateinit var viewModel: CartPageFragmentViewModel
    private lateinit var pdaoi: ProductsDaoInterface
    private lateinit var adapter: CartItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_cart_page, container, false)
        pdaoi= ApiUtils.getProductsDaoInterface()
        design.cartPageFragment=this

      //  design.emptyTxt
      /*  val swipegesture= object :SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){
                    ItemTouchHelper.LEFT ->{
                        viewModel.deleteCartItem(21,0)
                    } } } }
        val touchHelper= ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(rv_cart_items)
*/
        viewModel.cartItemsList.observe(viewLifecycleOwner, { cartItemsList ->
            adapter = CartItemsAdapter(requireContext(), cartItemsList,viewModel)
            design.cartadapter = adapter
        })
        design.rvCartItems.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)
        design.rvCartItems.addItemDecoration(DividerItemDecoration(activity,StaggeredGridLayoutManager.HORIZONTAL))

     /*  viewModel.cartItemsList.observe(viewLifecycleOwner,{
           if (it.size==0){
               design.emptyTxt.visibility=View.VISIBLE
           }
       })*/
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: CartPageFragmentViewModel by viewModels()
        viewModel=temp

    }

    fun deleteCartItem(id:Int,cart_status:Int){
        viewModel.deleteCartItem(id,cart_status)
        viewModel.success.observe(viewLifecycleOwner,{
            println(it)
            if (it == 1) {
                Toast.makeText(requireContext(),"Ürün Silindi", Toast.LENGTH_LONG).show()
                Log.e("55","silindi herhalde")
            } else if (it == 0) {
                Toast.makeText(requireContext(), "Giriş Yapılamadı!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}