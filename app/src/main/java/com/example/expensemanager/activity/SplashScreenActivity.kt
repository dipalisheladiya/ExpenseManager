package com.example.expensemanager.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIME_OUT = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initView()
    }
    private fun initView() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN_TIME_OUT.toLong()) // 3000 is the delayed time in milliseconds.
    }
}