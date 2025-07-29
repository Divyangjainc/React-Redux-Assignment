package com.example.wifitoggleapp

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var toggleButton: Button
    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton = findViewById(R.id.toggleButton)
        statusText = findViewById(R.id.statusText)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        updateStatus()

        toggleButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Android 10 and above - cannot toggle Wi-Fi directly
                val panelIntent = Intent(Settings.ACTION_WIFI_SETTINGS)
                startActivity(panelIntent)
            } else {
                // Android 9 and below
                val newState = !wifiManager.isWifiEnabled
                wifiManager.isWifiEnabled = newState
                updateStatus()
            }
        }
    }

    private fun updateStatus() {
        val state = if (wifiManager.isWifiEnabled) "ON" else "OFF"
        statusText.text = "Wi-Fi Status: $state"
    }
}
