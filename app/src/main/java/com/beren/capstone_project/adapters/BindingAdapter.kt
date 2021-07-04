package com.beren.capstone_project.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:set_price")
fun setPrice(textView: TextView,price:String){
    textView.text="$price \u20BA"
}