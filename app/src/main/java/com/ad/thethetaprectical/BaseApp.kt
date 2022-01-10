package com.ad.thethetaprectical

import android.app.Application
import com.ad.thethetaprectical.util.AppPreferences

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}