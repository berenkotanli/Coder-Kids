package com.beren.capstone_project.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beren.capstone_project.databinding.CardDesignBinding
import com.beren.capstone_project.databinding.CartCardDesignBinding
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.fragments.HomePageFragmentDirections
import com.beren.capstone_project.viewmodel.CartPageFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CartItemsAdapter (var mContext: Context, var productList: List<Products>,var viewModel: CartPageFragmentViewModel): RecyclerView.Adapter<CartItemsAdapter.CardDesignKeeper>() {

    inner class CardDesignKeeper(cartItemsBinding: CartCardDesignBinding): RecyclerView.ViewHolder(cartItemsBinding.root){
        var design: CartCardDesignBinding
        init {
            this.design=cartItemsBinding

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = CartCardDesignBinding.inflate(layoutInflater, parent, false)
        return CardDesignKeeper(design)
    }
    override fun onBindViewHolder(holder: CardDesignKeeper, position: Int) {
        val product=productList.get(position)
        holder.design.cartItemObject=product

        val sharedPreferences= mContext?.getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
        val editor= sharedPreferences?.edit()
        editor?.apply{
            putString("STRING_ITEM_COUNT",productList.size.toString())
        }?.apply()
        //Picasso part
        var url = "https://docs.google.com/uc?id="+product.urun_gorsel_url
        var id=holder.design.imageView
        Picasso.get().load(url).into(id);
        holder.design.title2Txt.text = product.urun_adi
       /* var totalprice=0
        for (i in productList){
            totalprice=totalprice+i.urun_fiyat.toInt()
        }
        holder.design.totalpricecart.text=totalPrice.toString()*/

        holder.design.deleteicon.setOnClickListener {
            Snackbar.make(it,"Ürünü silmek istiyor musunuz?",Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.deleteCartItem(product.id,0)
                    //viewModel.fetchCartProducts()
                    Snackbar.make(it,"Silindi",Snackbar.LENGTH_LONG).show()
                    notifyItemRemoved(position)
                }.show()

        }
    }
    override fun getItemCount(): Int {
        return productList.size
    }
   /* fun deleteItem(i:Int){
        viewModel.deleteCartItem(i,0)
    }*/
}