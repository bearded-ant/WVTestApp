package com.test.wvtestapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.test.wvtestapp.R


class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.main_fragmentContainerView) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        remoteConfigInit()
    }


    private fun remoteConfigInit() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 360
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (remoteConfig.getBoolean("flag")) {
                        viewModel.refreshConfigUrl(remoteConfig.getString("url"))
                        gotoWeb()
                    } else {
                        gotoGame()
                    }
                } else {
                    Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun gotoGame() {
        navController.navigate(R.id.gameMainFragment)
    }

    private fun gotoWeb() {
        navController.navigate(R.id.customWebViewFragment)
    }
}