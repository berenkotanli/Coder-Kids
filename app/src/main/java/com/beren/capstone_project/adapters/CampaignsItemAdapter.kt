package com.beren.capstone_project.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.RecyclerView
import com.beren.capstone_project.databinding.CampaignsCardDesignBinding
import com.beren.capstone_project.entity.Products
import com.beren.capstone_project.viewmodel.CampaignsPageFragmentViewModel
import com.squareup.picasso.Picasso

class CampaignsItemAdapter(var mContext: Context, var productList: List<Products>, var viewModel: CampaignsPageFragmentViewModel): RecyclerView.Adapter<CampaignsItemAdapter.CardDesignKeeper>() {
    inner class CardDesignKeeper(campaignItemBinding: CampaignsCardDesignBinding) : RecyclerView.ViewHolder(campaignItemBinding.root) {
        var design: CampaignsCardDesignBinding

        init {
            this.design = campaignItemBinding

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignsItemAdapter.CardDesignKeeper {
        val layoutInflater = LayoutInflater.from(mContext)

        val design = CampaignsCardDesignBinding.inflate(layoutInflater, parent, false)

        return CardDesignKeeper(design)
    }

    override fun onBindViewHolder(holder: CampaignsItemAdapter.CardDesignKeeper, position: Int) {
        val product=productList.get(position)
        holder.design.discountItemObject=product
        holder.design.titletv.text = product.urun_adi
        holder.design.pricetv.text = product.urun_aciklama
        holder.design.textViewprice.text=product.urun_fiyat
        holder.design.textViewpriceupdate.setPaintFlags(holder.design.textViewpriceupdate.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        holder.design.cardviewpromo.setOnClickListener {
            Toast.makeText(mContext,"Kampanya uygulandı", Toast.LENGTH_LONG).show()
        }
        //Picasso part
        var url = "https://docs.google.com/uc?id="+product.urun_gorsel_url
        var id=holder.design.bannerIv
        Picasso.get().load(url).into(id);
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
