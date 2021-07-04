package com.beren.capstone_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        //lottie
       // lottieAnimationView.setAnimation("splashteacher.json")
        val bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim)
        textViewanim.startAnimation(bottomanim)

        val splashscreenTimeout=4000
        val homeIntent= Intent(this@SplashScreen,LoginActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        },splashscreenTimeout.toLong())
    }

}