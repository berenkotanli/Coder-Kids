package com.beren.capstone_project

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beren.capstone_project.adapters.ViewPagerAdapter
import com.beren.capstone_project.fragments.LoginFragment
import com.beren.capstone_project.fragments.SignupFragment

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpTabs()
        layoutAnimator()
        tabLayoutAnimator()
    }

    private fun setUpTabs(){
        val adapter= ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(),title = "Login")
        adapter.addFragment(SignupFragment(),title = "Sign Up")
        view_pager.adapter=adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    fun tabLayoutAnimator(){
        val anim = ObjectAnimator.ofFloat(tab_layout,"translationX", -500.00f,0.0f)
                .apply {
                    duration=2000
                }
        anim.start()
    }

    fun layoutAnimator(){
        val anim = ObjectAnimator.ofFloat(constraint_layout2,"translationY", 500.00f,0.0f)
                .apply {
                    duration=2000
                }
        anim.start()
    }


    }

