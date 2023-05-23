package com.test.wvtestapp.base

import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.onesignal.OneSignal

const val AF_DEV_KEY: String = "T3VrWscGpfMfy68HLCjAX6"
const val ONESIGNAL_APP_ID: String = "82138481-c06a-4125-be89-e3a04f184800"

class App : Application() {
    override fun onCreate() {
        super.onCreate()

//        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
//
//        val metricks: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
//            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onConversionDataFail(p0: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onAttributionFailure(p0: String?) {
//                TODO("Not yet implemented")
//            }
//        }
//
//        AppsFlyerLib.getInstance().setDebugLog(true)
//        AppsFlyerLib.getInstance().init(AF_DEV_KEY, metricks, this)
//        AppsFlyerLib.getInstance().registerConversionListener(this, metricks)
//        AppsFlyerLib.getInstance().start(this)

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
    }

    fun flyerInit() {
        AppsFlyerLib.getInstance().start(this,
            AF_DEV_KEY, object : AppsFlyerRequestListener {
                override fun onSuccess() {
                    Log.d("FlyerLog", "Launch sent successfully")
                }

                override fun onError(errorCode: Int, errorDesc: String) {
                    Log.d(
                        "FlyerLog", "Launch failed to be sent:\n" +
                                "Error code: " + errorCode + "\n"
                                + "Error description: " + errorDesc
                    )
                }
            })
    }
}