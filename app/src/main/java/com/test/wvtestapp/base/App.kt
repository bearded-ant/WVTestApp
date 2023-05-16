package com.test.wvtestapp.base

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

const val AF_DEV_KEY: String = "0"
const val ONESIGNAL_APP_ID: String = "1"

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
    }
}