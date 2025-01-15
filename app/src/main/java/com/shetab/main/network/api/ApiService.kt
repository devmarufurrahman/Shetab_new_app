package com.shetab.main.network.api

import com.shetab.main.model.StudentVisaModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("visa/apply") // Replace with your endpoint
    fun submitVisaApplication(
        @Part("fullName") fullName: String,
        @Part("dateOfBirth") dateOfBirth: String,
        @Part("maritalStatus") maritalStatus: String,
        @Part("email") email: String,
        @Part("phoneNumber") phoneNumber: String,
        @Part("homeAddress") homeAddress: String,
        @Part("country") country: String,
        @Part("semester") semester: String,
        @Part("degree") degree: String,
        @Part("desiredMajor") desiredMajor: String,
        @Part transcripts: List<MultipartBody.Part>,
        @Part otherCertificates: List<MultipartBody.Part>,
        @Part resume: MultipartBody.Part,
        @Part passport: MultipartBody.Part,
        @Part photo: MultipartBody.Part
    ): Call<StudentVisaModel>
}