package com.shetab.main.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object LocationPermission {
    private const val LOCATION_PERMISSION_REQUEST_CODE = 100

    // Check if location permissions are granted
    fun isLocationPermissionGranted(activity: Activity): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Request location permissions
    private fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    // Handle permission result
    fun handlePermissionResult(
        requestCode: Int,
        grantResults: IntArray,
        onGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onGranted()
            } else {
                onDenied()
            }
        }
    }


    fun showLocationPermissionDialog(context: Context, activity: Activity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Location Permission Required")
        builder.setMessage("This app requires location permission to mark attendance based on your location. Please grant location access to continue.")
        builder.setPositiveButton("Grant Permission") { dialog, _ ->
            requestLocationPermission(activity)
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(context, "Permission denied. Attendance cannot be marked.", Toast.LENGTH_SHORT).show()
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()
    }


}