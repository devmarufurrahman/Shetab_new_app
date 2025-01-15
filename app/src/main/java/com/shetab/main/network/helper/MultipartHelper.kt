package com.shetab.main.network.helper

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

// Helper function to create a RequestBody from a string
fun createPartFromString(value: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
}

// Helper function to prepare a file for multipart upload
fun prepareFilePart(partName: String, filePath: String): MultipartBody.Part {
    val file = File(filePath)
    val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
}