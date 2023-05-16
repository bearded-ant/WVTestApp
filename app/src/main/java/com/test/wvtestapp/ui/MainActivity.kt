package com.test.wvtestapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.test.wvtestapp.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppsFlyerLib.getInstance().start(this, "YOUR_DEV_KEY", object : AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d("FlyerLog", "Launch sent successfully")
            }

            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d("FlyerLog", "Launch failed to be sent:\n" +
                        "Error code: " + errorCode + "\n"
                        + "Error description: " + errorDesc)
            }
        })

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CustomWebViewFragment.newInstance())
            .commit()
    }
}