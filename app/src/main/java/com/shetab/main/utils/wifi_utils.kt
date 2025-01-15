package com.shetab.main.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.core.app.ActivityCompat

class WifiUtil(private val context: Context) {

    fun getWifiName(): String {
        // Check if we have the necessary permissions
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return "Permission not granted"
        }

        // Get the WifiManager system service
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // Get the current connection info (WifiInfo)
        val wifiInfo: WifiInfo = wifiManager.connectionInfo

        // Get the SSID of the currently connected Wi-Fi network
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // From Android 10 (API level 29) onwards, Wi-Fi SSID is restricted for privacy reasons.
            // You need to use the WifiNetworkSpecifier or Wi-Fi scanning APIs in these versions.
            if (wifiInfo.ssid != null && wifiInfo.ssid.isNotEmpty()) {
                wifiInfo.ssid
            } else {
                "SSID not available for Android 10+"
            }
        } else {
            // For earlier versions, you can directly get the SSID
            if (wifiInfo.ssid != null && wifiInfo.ssid.isNotEmpty()) {
                wifiInfo.ssid
            } else {
                "SSID not available for Android 9 and below"
            }
        }
    }
}