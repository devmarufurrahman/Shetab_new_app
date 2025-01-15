package com.shetab.main.ui.viewmodel


import android.app.Application
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AttendanceViewModel(application: Application) : AndroidViewModel(application) {

    val userName = MutableLiveData("Abbas Talukdar")
    val designation = MutableLiveData("HR Admin")
    val currentLocation = MutableLiveData("Fetching location...")
    val officeLocation = MutableLiveData("Office Location: House #13, Road #11, Gudaraghat, Uttar Badda, Dhaka-1212")
    val inTime = MutableLiveData("--:--")
    val outTime = MutableLiveData("--:--")
    var withinRange : Boolean = false

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    fun fetchOfficeLocation(){
        var lat : Double = 23.78385669
        var long : Double = 90.42196820

        getGeoLocation(lat, long, "office")

    }

    fun fetchCurrentLocation() {
        val cancellationTokenSource = CancellationTokenSource()

        try {
            fusedLocationProviderClient.getCurrentLocation(
                com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    withinRange = isWithinRange(
                        location.latitude,
                        location.longitude,
                        23.78385669, // Office Latitude
                        90.42196820  // Office Longitude
                    )
                    if (withinRange) {

                        currentLocation.postValue("Within allowed range")
                    } else {
                        currentLocation.postValue("Outside allowed range")
                        // Show toast for being out of range
                        Toast.makeText(
                            getApplication(),
                            "You are not within the allowed range to mark attendance",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                   // getGeoLocation(location.latitude, location.longitude, "current")
                } else {
                    currentLocation.postValue("Unable to fetch location")
                }
            }.addOnFailureListener { exception ->
                currentLocation.postValue("Error fetching location: ${exception.message}")
            }
        } catch (e: SecurityException) {
            currentLocation.postValue("Permission not granted")
        }
    }

    private fun getGeoLocation(latitude: Double, longitude: Double, type:String) {
        try {
            val geocoder = Geocoder(getApplication(), Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                println(address)
                if(type == "office"){
                    officeLocation.postValue("Office Location: ${address.getAddressLine(0)}")
                } else if(type == "current"){
                    currentLocation.postValue(
                        "Current Location: ${address.getAddressLine(0)}"
                    )
                }
            } else {
                currentLocation.postValue("Unable to fetch location details")
            }
        } catch (e: Exception) {
            currentLocation.postValue("Geo-location error: ${e.message}")
        }
    }

    fun isWithinRange(currentLat: Double, currentLong: Double, officeLat: Double, officeLong: Double): Boolean {
        val officeLocation = Location("").apply {
            latitude = officeLat
            longitude = officeLong
        }

        val currentLocation = Location("").apply {
            latitude = currentLat
            longitude = currentLong
        }

        val distance = officeLocation.distanceTo(currentLocation) // Distance in meters
        return distance <= 50 // Check if within 50 meters
    }



    fun setInTime() {
        val currentTime = System.currentTimeMillis()
        val formattedTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(currentTime))
        inTime.postValue(formattedTime.toString())
    }

    fun setOutTime() {
        val currentTime = System.currentTimeMillis()
        val formattedTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(currentTime))
        outTime.postValue(formattedTime.toString())
    }
}