package com.shetab.main.service

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.shetab.main.R
import com.shetab.main.adapter.Service
import com.shetab.main.ui.activity.ApplyVisaActivity
import com.shetab.main.ui.activity.FaqActivity

object HomeService {

    fun handleServiceClick(service: Service, context: Context) {
        when (service.name) {
            context.getString(R.string.apply_visa) -> {
                // Action for "Apply Visa"
                context.startActivity(Intent(context, ApplyVisaActivity::class.java))
            }
            context.getString(R.string.faq) -> {
                // Action for "FAQ"
                context.startActivity(Intent(context, FaqActivity::class.java))
            }
            "ব্লাড ডোনার" -> {
                // Action for "ব্লাড ডোনার"
//                startActivity(Intent(this, BloodDonorActivity::class.java))
            }
            else -> {
                // Default action or a message
                Log.d("ServiceClick", "Unknown service: ${service.name}")
                Toast.makeText(context, "Coming Soon: ${service.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}