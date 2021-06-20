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
        createProduct()
        //getProducts()



    }
    fun createProduct(){
        pdaoi.createProduct("berenkotanli","Scratch Eğitimi","219,99","Scratch, küçük yaştaki çocuklar için tercih edilen bir giriş programlama platformudur. Scratch ile Animasyon ve Oyunlar, programlamayı öğrenmek isteyen çocuklar için harika bir giriş dersidir. Öğrenciler, Scratch'in kullanımı kolay arayüzünü kullanarak güçlü programlama kavramlarını öğrenecekler. Her ders, değişkenleri kullanma ve oluşturma, döngüleri uygulama ve koşullu ifadeleri kullanma gibi programlama için çok önemli olan becerileri öğreten bir oyun veya animasyon oluşturmaya harcanır. Dersin sonunda öğrenciler basit Scratch oyunlarını ve animasyonlarını kodlama becerisine sahip olacaklardır.","https://drive.google.com/file/d/1phLSklIyFOClt_I-lAs1sJ1kMEKVA5c6/view?usp=sharing",0,0,55)
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

                    Log.e("2",p.urun_adi)
                    Log.e("2",p.urun_aciklama)
                    Log.e("2",p.urun_fiyat)
                    Log.e("2",p.urun_gorsel_url)
                    Log.e("2",p.urun_indirimli_mi.toString())
                }
            }

            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {

            }

        })
    }
}