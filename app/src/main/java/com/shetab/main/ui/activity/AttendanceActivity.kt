package com.shetab.main.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.shetab.main.R
import com.shetab.main.databinding.ActivityAttendanceBinding
import com.shetab.main.utils.LocationPermission
import com.shetab.main.utils.NetworkUtil
import com.shetab.main.utils.WifiUtil
import com.shetab.main.ui.viewmodel.AttendanceViewModel

class AttendanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceBinding
    private val viewModel: AttendanceViewModel by viewModels()
    val wifiUtil = WifiUtil(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val wifiName = wifiUtil.getWifiName()
        Toast.makeText(this, wifiName, Toast.LENGTH_SHORT).show()
        println(wifiName)
        val networkUtil = NetworkUtil(this)
        val networkType = networkUtil.getNetworkType()
        Toast.makeText(this, "Network Type: $networkType", Toast.LENGTH_SHORT).show()
        println(networkType)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        try {
            // Check and request location permissions
            if (!LocationPermission.isLocationPermissionGranted(this)) {
                LocationPermission.showLocationPermissionDialog(this, this)
            } else {
                // Fetch current location
                viewModel.fetchCurrentLocation()

            }
        } catch (e:Exception){
            e.printStackTrace()
        }

        binding.btnInTime.setOnClickListener{
            if (networkType == "Connected to Wi-Fi" && wifiName == "\"Shetab\""){
                if (viewModel.withinRange) {
                    viewModel.setInTime()
                } else {
                    Toast.makeText(this, "You are not within the allowed range to mark attendance", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "You are not connected to the required network", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnOutTime.setOnClickListener{
            if (networkType == "Connected to Wi-Fi" && wifiName == "\"Shetab\""){
                if (viewModel.withinRange) {
                    viewModel.setOutTime()
                } else {
                    Toast.makeText(this, "You are not within the allowed range to mark attendance", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "You are not connected to the required network", Toast.LENGTH_SHORT).show()
            }

        }



    }
}