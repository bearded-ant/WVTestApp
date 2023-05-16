package com.test.wvtestapp.base

import android.app.Application
import com.appsflyer.AppsFlyerLib

const val AF_DEV_KEY: String = "0"

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
    }
}