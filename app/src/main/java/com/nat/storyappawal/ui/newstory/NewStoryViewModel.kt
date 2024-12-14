package com.nat.storyappawal.ui.newstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nat.storyappawal.data.api.response.FileUploadResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class NewStoryViewModel() : ViewModel() {

    private val _fileUploadResponse = MutableLiveData<FileUploadResponse>()
    val fileUploadResponse: LiveData<FileUploadResponse> = _fileUploadResponse

    fun uploadStory(
        image: MultipartBody.Part,
        title: RequestBody,
        content: RequestBody
    ) {
        viewModelScope.launch {
        }
    }
}
