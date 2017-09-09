package com.nhphong.fitnesschallenge.activities

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.nhphong.fitnesschallenge.Firebase
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.adapters.ScreenSlidePagerAdapter
import com.nhphong.fitnesschallenge.databinding.ActivityMainBinding
import com.nhphong.fitnesschallenge.databinding.TabLayoutBinding
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.getInstance().fetchRemoteConfigs(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val inflater = LayoutInflater.from(this)
        for (i in 0 until adapter.count) {
            val tabBinding = TabLayoutBinding.inflate(inflater, null)
            tabBinding.textView.text = adapter.getPageTitle(i)
            tabBinding.icon.setImageResource(adapter.getPageIcon(i))
            binding.tabLayout.getTabAt(i)?.customView = tabBinding.root
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
