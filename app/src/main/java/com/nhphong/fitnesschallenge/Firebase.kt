package com.nhphong.fitnesschallenge

import android.app.Activity
import com.google.firebase.database.FirebaseDatabase
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

    val database = FirebaseDatabase.getInstance().reference
    val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        setDefaults(R.xml.remote_config_defaults)
    }

    fun fetchRemoteConfigs(activity: Activity): Single<Ignore> = Single.create<Ignore> { subscriber ->
        remoteConfig.fetch().addOnCompleteListener(activity) { task ->
            when {
                task.isSuccessful -> {
                    remoteConfig.activateFetched()
                    subscriber.onSuccess(IGNORE)
                }
                else -> subscriber.onError(Throwable(activity.getString(R.string.failed_to_load_firebase_rc)))
            }
        }
    }
}
