package com.nhphong.fitnesschallenge

import android.app.Activity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.nhphong.fitnesschallenge.misc.IGNORE
import com.nhphong.fitnesschallenge.misc.Ignore
import io.reactivex.Single

class Firebase private constructor() {

    companion object {
        private var INSTANCE: Firebase? = null

        @Synchronized
        fun getInstance(): Firebase {
            if (INSTANCE == null) {
                INSTANCE = Firebase()
            }
            return INSTANCE!!
        }
    }

    private val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        setDefaults(R.xml.remote_config_defaults)
    }

    fun fetchRemoteConfigs(activity: Activity): Single<Ignore> = Single.create<Ignore> { subscriber ->
        firebaseRemoteConfig.fetch().addOnCompleteListener(activity) { task ->
            when {
                task.isSuccessful -> {
                    firebaseRemoteConfig.activateFetched()
                    subscriber.onSuccess(IGNORE)
                }
                else -> subscriber.onError(Throwable(activity.getString(R.string.failed_to_load_firebase_rc)))
            }
        }
    }

    fun getString(key: String): String = firebaseRemoteConfig.getString(key)
}
