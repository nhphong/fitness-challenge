package com.nhphong.fitnesschallenge

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class App: Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Just tell me what.otf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}
