package com.test.wvtestapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.test.wvtestapp.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flyerInit()
        remoteConfigInit()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CustomWebViewFragment.newInstance())
            .commit()
    }

    private fun flyerInit() {
        AppsFlyerLib.getInstance().start(this, "YOUR_DEV_KEY", object : AppsFlyerRequestListener {
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


    private fun remoteConfigInit() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d("TAG", "Config params updated: $updated")
                    Toast.makeText(this, "Fetch and activate succeeded", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
                }
//                displayWelcomeMessage()
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            }

    }
}