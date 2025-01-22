package com.shetab.main.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shetab.main.R
import com.shetab.main.databinding.ActivityApplyVisaBinding
import com.shetab.main.databinding.ActivityFaqBinding

class FaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idBackMainBack.setOnClickListener{
            finish()
        }

        // Set up click listeners for FAQ items
        setupFaqClickListeners()

    }
    private fun setupFaqClickListeners() {
        // FAQ 1
        binding.faqMainLay1.setOnClickListener {
            toggleFaqVisibility(binding.faqAnsListLay1, binding.faqImgArrow1)
        }

        // FAQ 2
        binding.faqMainLay2.setOnClickListener {
            toggleFaqVisibility(binding.faqAnsListLay2, binding.faqImgArrow2)
        }

        // FAQ 3
        binding.faqMainLay3.setOnClickListener {
            toggleFaqVisibility(binding.faqAnsListLay3, binding.faqImgArrow3)
        }

        // FAQ 4
        binding.faqMainLay4.setOnClickListener {
            toggleFaqVisibility(binding.faqAnsListLay4, binding.faqImgArrow4)
        }

        // FAQ 5
        binding.faqMainLay5.setOnClickListener {
            toggleFaqVisibility(binding.faqAnsListLay5, binding.faqImgArrow5)
        }
    }

    private fun toggleFaqVisibility(answerLayout: View, arrowIcon: View) {
        if (answerLayout.visibility == View.GONE) {
            answerLayout.visibility = View.VISIBLE
            arrowIcon.rotation = 180f // Rotate the arrow downwards
        } else {
            answerLayout.visibility = View.GONE
            arrowIcon.rotation = 0f // Reset the arrow to its default position
        }
    }

}