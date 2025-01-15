package com.shetab.main.model

data class StudentVisaModel(
    val fullName: String,
    val dateOfBirth: String,
    val maritalStatus: String,
    val email: String,
    val phoneNumber: String,
    val homeAddress: String,
    val country: String,
    val semester: String,
    val degree: String,
    val transcripts: List<String>,
    val otherCertificates: List<String>,
    val resume: String,
    val passport: String,
    val photo: String,
    val desiredMajor: String
)

