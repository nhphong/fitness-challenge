package com.nhphong.fitnesschallenge.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.YoutubeFragment
import com.nhphong.fitnesschallenge.databinding.ActivityYoutubeBinding

class YoutubeActivity: AppCompatActivity() {

    companion object {
        private const val EXTRA_VIDEO_URL = "Extra Video Url"

        fun launch(context: Context, videoUrl: String) {
            context.startActivity(Intent(context, YoutubeActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_URL, videoUrl)
            })
        }
    }

    private lateinit var binding: ActivityYoutubeBinding
    private lateinit var videoUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube)
        videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL)

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, YoutubeFragment.newInstance(videoUrl))
                .addToBackStack(null)
                .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getYoutubeFragment()?.onActivityResult(requestCode, resultCode, data)
    }

    private fun getYoutubeFragment()
            = fragmentManager.findFragmentById(R.id.fragment_container) as YoutubeFragment?
}
