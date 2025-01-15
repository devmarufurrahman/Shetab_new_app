package com.shetab.main.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.shetab.main.R
import com.shetab.main.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.logoSplashActivity.startAnimation(fadeInAnimation)
        var value = "";



        Handler(Looper.getMainLooper()).postDelayed({

                if (value != null || value != "") {
                    Log.d("data local splash token", value)
                    //toast("success")
                    startActivity(Intent(this, HomeActivity:: class.java))
                    finish()

                } else {
                    Log.d("data local splash token", "token is null")
//                    startActivity(Intent(this, HomePage:: class.java))
//                    finish()
                }

        }, 1500)


    }
}