package com.shetab.main.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationView
import com.shetab.main.R
import com.shetab.main.adapter.DashboardAdapter
import com.shetab.main.adapter.Service
import com.shetab.main.databinding.ActivityHomeBinding
import com.shetab.main.service.HomeService
import com.shetab.main.utils.LocationPermission

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayoutVar: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setContentView(binding.root)

        // Initialize Views
        binding.apply {
            drawerLayoutVar = drawerLayout
            navigationView = navMenu
            toolbar = customToolbar
        }


//        side navigation view start here ================================================
        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu) // menu icon

//        setup dashboard
        setupRecyclerView()
        setSlider()


        // Handle the back button using OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayoutVar.isDrawerOpen(GravityCompat.START)) {
                    drawerLayoutVar.closeDrawer(GravityCompat.START)
                } else {
                    // If you need to handle back press for fragments or other UI components, do it here.
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        // Set Navigation Listener
        navigationView.setNavigationItemSelectedListener(this)

//        navigation header listener
        val headerView = binding.navMenu.getHeaderView(0)
        val userNameTv = headerView.findViewById<TextView>(R.id.textViewUserName)
//        Side navigation view end here ================================================


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        LocationPermission.handlePermissionResult(
            requestCode,
            grantResults,
            onGranted = {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                // Proceed with location-based functionality
            },
            onDenied = {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                // Handle the case where permission is denied
            }
        )
    }


    // Handle Navigation Item Clicks
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.main_menu_setting -> {
//
//            }
//
//            R.id.main_menu_profile -> {
//
//            }
//
//            R.id.main_menu_help -> {
//
//            }
//
//            R.id.main_menu_attendance -> {
//                try {
//                    // Check and request location permissions
//                    if (!LocationPermission.isLocationPermissionGranted(this)) {
//                        LocationPermission.showLocationPermissionDialog(this, this)
//                    } else {
//                        val intent = Intent(this, AttendanceActivity::class.java)
//                        startActivity(intent)
//                    }
//                } catch (e:Exception){
//                    e.printStackTrace()
//                }
//            }
//
//            R.id.main_menu_logout -> {
//                // Handle logout
//
//            }

        }
        drawerLayoutVar.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupRecyclerView() {
        val services = listOf(
            Service(getString(R.string.apply_visa), R.drawable.baseline_airplanemode_active_24),
            Service(getString(R.string.faq), R.drawable.help_icon_orange),
            Service(getString(R.string.live_chat), R.drawable.ic_chat_orange),
            Service(getString(R.string.book_appointment), R.drawable.ic_appointment),
            Service(getString(R.string.appointment_status), R.drawable.ic_appointment_manage),
            Service(getString(R.string.manage_appointment), R.drawable.ic_chat_manage),
            Service(getString(R.string.my_applications), R.drawable.ic_my_application),
            Service(getString(R.string.manage_application), R.drawable.ic_chat_orange),
            Service(getString(R.string.my_applicant_s_agent), R.drawable.ic_application_agent),
            Service(getString(R.string.activity_log), R.drawable.ic_activity_log),
            // Add more services here
        )

        binding.servicesRecyclerView.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            adapter = DashboardAdapter(services) { service ->
                HomeService.handleServiceClick(service, this@HomeActivity)
            }
        }
    }

//    slider images
    fun setSlider(){
    val imageList = ArrayList<SlideModel>()

    imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtT9ftAsl0BzOag4qNureAPTtA0OmeuOgrakghJrnuVGvwUb7Po4XqzvPOt0rH89EwvR4&usqp=CAU", "Labor Placement"))
    imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXkDK0LfTWKAZwerV3pBNNmKkFh0LizFn0-0xyCKZTrcOCfNkVU-lhdPP959cxMj8TN0E&usqp=CAU", "Student Placement"))
    imageList.add(SlideModel("https://5.imimg.com/data5/SELLER/Default/2023/3/DQ/WF/ET/185758929/tourist-visa-consultancy-services-500x500.jpeg", "Tourist Visa Consultancy"))

    binding.imageSlider.setImageList(imageList)
}


    // Handle Toolbar Menu Clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayoutVar.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}