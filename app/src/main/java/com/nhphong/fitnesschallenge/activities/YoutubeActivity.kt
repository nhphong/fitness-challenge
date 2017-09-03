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
        private const val EXTRA_VIDEO_ID = "Extra Video Id"

        fun launch(context: Context, videoId: String) {
            context.startActivity(Intent(context, YoutubeActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_ID, videoId)
            })
        }
    }

    private lateinit var binding: ActivityYoutubeBinding
    private lateinit var videoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube)
        videoId = intent.getStringExtra(EXTRA_VIDEO_ID)

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, YoutubeFragment.newInstance(videoId))
                .addToBackStack(null)
                .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getYoutubeFragment()?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun getYoutubeFragment()
            = fragmentManager.findFragmentById(R.id.fragment_container) as YoutubeFragment?
}
