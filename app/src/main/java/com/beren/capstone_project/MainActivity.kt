package com.beren.capstone_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.beren.capstone_project.entity.CRUDResponse
import com.beren.capstone_project.entity.ProductsResponse
import com.beren.capstone_project.retrofit.ApiUtils
import com.beren.capstone_project.retrofit.ProductsDaoInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pdaoi: ProductsDaoInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navhost=supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomBar,navhost.navController)

      /*  val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomBar,navHostFragment.navController)*/



        pdaoi=ApiUtils.getProductsDaoInterface()
        //createProduct()
       getProducts()



    }
    fun createProduct(){
        pdaoi.createProduct("berenkotanli","Code.org Eğitimi Fırsatı","89,99","Erken kayıt yaptır, indirimden faydalan. Sadece 3 gün için geçerli!", "1xc_am-sVdzPjHUfRJ2Rb5FpdJVHgr2yS",1,0,10)
                .enqueue(object : Callback<CRUDResponse>{
                    override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                        Log.e("Başarı",response.body().success.toString())
                        Log.e("Mesaj",response.body().message)
                    }

                    override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                        Log.e("fail","fail")
                    }

                })
    }
    fun getProducts(){
        pdaoi.getProducts("berenkotanli").enqueue(object : Callback<ProductsResponse>{
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>) {
                val product=response.body().products
                for(p in product){

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