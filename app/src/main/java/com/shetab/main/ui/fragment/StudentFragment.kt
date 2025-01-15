package com.shetab.main.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shetab.main.databinding.FragmentStudentBinding
import com.shetab.main.model.StudentVisaModel
import com.shetab.main.network.helper.prepareFilePart
import com.shetab.main.network.retrofit.RetrofitClient
import retrofit2.Call

class StudentFragment : Fragment() {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        binding.applyVisaBtnStudent.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            submitForm()
        }
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun submitForm() {
        val fullName = binding.applyVisaName.text.toString()
        val dateOfBirth = ""
        val maritalStatus = ""
        val email = binding.applyVisaEmail.text.toString()
        val phoneNumber = binding.applyVisaPhnNo.text.toString()
        val homeAddress = binding.applyVisaAddress.text.toString()
        val country = ""
        val semester = ""
        val degree = ""
        val desiredMajor = ""

        val transcripts = listOf(
            prepareFilePart("transcripts", "/path/to/transcript1.pdf"),
            prepareFilePart("transcripts", "/path/to/transcript2.pdf")
        )

        val otherCertificates = listOf(
            prepareFilePart("otherCertificates", "/path/to/certificate1.pdf")
        )

        val resume = prepareFilePart("resume", "/path/to/resume.pdf")
        val passport = prepareFilePart("passport", "/path/to/passport.pdf")
        val photo = prepareFilePart("photo", "/path/to/photo.jpg")

        RetrofitClient.apiService.submitVisaApplication(
            fullName,
            dateOfBirth,
            maritalStatus,
            email,
            phoneNumber,
            homeAddress,
            country,
            semester,
            degree,
            desiredMajor,
            transcripts,
            otherCertificates,
            resume,
            passport,
            photo
        ).enqueue(object : retrofit2.Callback<StudentVisaModel> {
            override fun onResponse(call: Call<StudentVisaModel>, response: retrofit2.Response<StudentVisaModel>) {
                if (response.isSuccessful) {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, "Submission failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StudentVisaModel>, t: Throwable) {
                binding.progressbar.visibility = View.GONE
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("error", "onFailure: ${t.message}")
            }
        })
    }

}