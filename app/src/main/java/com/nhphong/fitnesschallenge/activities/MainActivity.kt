package com.nhphong.fitnesschallenge.activities

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.adapters.ScreenSlidePagerAdapter
import com.nhphong.fitnesschallenge.databinding.ActivityMainBinding
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        binding.viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
