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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomBar,navHostFragment.navController)

        pdaoi=ApiUtils.getProductsDaoInterface()
        //createProduct()
        getProducts()



    }
    fun createProduct(){
        pdaoi.createProduct("berenkotanli","Arduino Eğitimi","219,99","Arduino, temel elektronik, otomasyon ve robotik becerilerine sahip hem çocuklar hem de yetişkinler için mükemmel bir seçimdir. Arduino platformu temelinde bir robot araba, küçük bir lamba, bir akıllı ev elemanı monte etmek ve programlamak mümkündür. Programlamanın kendisi, C programlama diline dayanan Arduino IDE adlı özel bir ortam aracılığıyla etkinleştirilir. Bu eğitimin sonunda öğrenciler robotik kodlamanın temellerini ve önemli bilgisayar bilimi konularını öğrenecek. Bunun için Autodesk tarafından tasarlanan eğlenceli programlama ve elektronik simülatör aracı Tinkercad kullanılacak. Arduino'nun nasıl programlanacağı, sensörleri, elektrik motorlarının nasıl kullanılacağı ve gerçek hayattaki elektronik devrelerinin nasıl oluşturulacağı öğrenilecektir. Bu eğitim, derslerin sıkıcı veya karmaşık ayrıntılarına girmeden belirli programlama ve elektronik kavramlarını hedefleyecek şekilde dikkatlice tasarlandı. Herhangi bir programlama veya elektronikle ilgili deneyim gerektirmez.","1MpvnUofF6_XoCZS4F4uTFNyF5lZflGQw",0,0,10)
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
                    Log.e("2",p.urun_aciklama)
                    Log.e("3",p.urun_fiyat)
                    Log.e("4",p.urun_gorsel_url)
                    Log.e("5",p.urun_indirimli_mi.toString())
                }
            }

            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {

            }

        })
    }
}