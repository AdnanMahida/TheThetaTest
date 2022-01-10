package com.ad.thethetaprectical

import android.app.Application

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}