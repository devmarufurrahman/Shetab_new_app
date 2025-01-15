package com.shetab.main.utils
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager

class NetworkUtil(private val context: Context) {

    fun getNetworkType(): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Get the active network info
        val networkInfo = connectivityManager.activeNetworkInfo

        // If there is no active network, return "No connection"
        if (networkInfo == null || !networkInfo.isConnected) {
            return "No connection"
        }

        // Check if the active network is Wi-Fi
        if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
            return "Connected to Wi-Fi"
        }

        // Check if the active network is mobile data
        if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return "Connected to Mobile Network"
        }

        return "Unknown Network Type"
    }
}
