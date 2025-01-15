package com.shetab.main.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.shetab.main.databinding.ActivityApplyVisaBinding
import com.shetab.main.ui.fragment.StudentFragment
import com.shetab.main.ui.fragment.VisitFragment
import com.shetab.main.ui.fragment.WorkerFragment

class ApplyVisaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApplyVisaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyVisaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupViewPager()

    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Back button functionality
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupViewPager() {
        // Set up ViewPager2 adapter
        val adapter = VisaPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Attach TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Student"
                1 -> "Worker"
                2 -> "Visit"
                else -> "Page ${position + 1}"
            }
        }.attach()
    }

    // Adapter for ViewPager2
    private inner class VisaPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // Total number of pages

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> StudentFragment() // Fragment for "Student"
                1 -> WorkerFragment()  // Fragment for "Worker"
                2 -> VisitFragment()   // Fragment for "Visit"
                else -> throw IllegalStateException("Unexpected position: $position")
            }
        }
    }
}