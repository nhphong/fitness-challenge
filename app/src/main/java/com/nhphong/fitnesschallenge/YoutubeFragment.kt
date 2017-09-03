package com.nhphong.fitnesschallenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment

class YoutubeFragment : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener {
    companion object {
        private const val ARG_VIDEO_ID = "Argument Video Id"
        private const val RECOVERY_DIALOG_REQUEST_CODE = 100

        fun newInstance(videoId: String) = YoutubeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_ID, videoId)
            }
        }
    }

    private lateinit var videoId: String
    private var player: YouTubePlayer? = null

    override fun onCreateView(inflater: LayoutInflater?, viewGroup: ViewGroup?, bundle: Bundle?): View {
        videoId = arguments.getString(ARG_VIDEO_ID)
        initialize(BuildConfig.GOOGLE_APP_KEY, this)
        return super.onCreateView(inflater, viewGroup, bundle)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_DIALOG_REQUEST_CODE) {
            initialize(BuildConfig.GOOGLE_APP_KEY, this)
        }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, wasRestored: Boolean) {
        player = youtubePlayer
        player?.cueVideo(videoId)
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        errorReason?.run {
            if (errorReason.isUserRecoverableError) {
                errorReason.getErrorDialog(activity, RECOVERY_DIALOG_REQUEST_CODE).show()
            } else {
                Toast.makeText(activity, errorReason.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
