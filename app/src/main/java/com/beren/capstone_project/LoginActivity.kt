package com.beren.capstone_project

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beren.capstone_project.adapters.ViewPagerAdapter
import com.beren.capstone_project.entity.ProductsResponse
import com.beren.capstone_project.fragments.LoginFragment
import com.beren.capstone_project.fragments.SignupFragment
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var pdaoi: ProductsDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pdaoi = ApiUtils.getProductsDaoInterface()
        setUpTabs()
        layoutAnimator()
        tabLayoutAnimator()
       // fetchCartProducts(1)
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), title = "Giriş Yap")
        adapter.addFragment(SignupFragment(), title = "Kaydol")
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    fun tabLayoutAnimator() {
        val anim = ObjectAnimator.ofFloat(tab_layout, "translationX", -500.00f, 0.0f)
            .apply {
                duration = 2000
            }
        anim.start()
    }

    fun layoutAnimator() {
        val anim = ObjectAnimator.ofFloat(constraint_layout2, "translationY", 500.00f, 0.0f)
            .apply {
                duration = 2000
            }
        anim.start()
    }

    /* fun fetchCartProducts(cart_status:Int) {
        pdaoi.fetchCartProducts(cart_status).enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>) {
                val list = response.body().products
                //cartProductsList.value=list
                for (p in list){
                    Log.e("************","************")

                    Log.e("1",p.urun_adi)
                    Log.e("7",p.id.toString())
                    Log.e("2",p.urun_aciklama)
                    Log.e("3",p.urun_fiyat)
                    Log.e("4",p.urun_gorsel_url)
                    Log.e("5",p.urun_indirimli_mi.toString())
                    Log.e("6",p.sepet_durum.toString())
                }
            }
            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {
            }
        })
    }
    }
    */
}
