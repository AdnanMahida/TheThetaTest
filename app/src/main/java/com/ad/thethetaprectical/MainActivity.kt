package com.ad.thethetaprectical

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ad.thethetaprectical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}