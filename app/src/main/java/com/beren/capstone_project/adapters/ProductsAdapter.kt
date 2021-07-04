package com.beren.capstone_project.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beren.capstone_project.databinding.CardDesignBinding
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.fragments.HomePageFragmentDirections

import com.squareup.picasso.Picasso

class ProductsAdapter(var mContext: Context, var productList: List<Products>): RecyclerView.Adapter<ProductsAdapter.CardDesignKeeper>() {

    inner class CardDesignKeeper(productCardBinding: CardDesignBinding):RecyclerView.ViewHolder(productCardBinding.root){
        var design: CardDesignBinding
        init {
            this.design=productCardBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)

        val design = CardDesignBinding.inflate(layoutInflater, parent, false)

        return CardDesignKeeper(design)
    }

    override fun onBindViewHolder(holder: CardDesignKeeper, position: Int) {

                val product=productList.get(position)
                holder.design.productObject=product

                //Picasso part
                var url = "https://docs.google.com/uc?id="+product.urun_gorsel_url
                var id=holder.design.pic
                Picasso.get().load(url).into(id);

                Log.e("66",product.urun_gorsel_url)

                holder.design.title.text = product.urun_adi
                holder.design.fee.text = product.urun_fiyat

                holder.design.cardConst.setOnClickListener {
                    var transition= HomePageFragmentDirections.actionToDetails(product)
                    Navigation.findNavController(it).navigate(transition)
                }
               // holder.design.addBtn.set
}
    override fun getItemCount(): Int {
        return productList.size
    }
}