package com.nat.storyappawal.data.di

import android.content.Context
import com.nat.storyappawal.data.api.ApiConfig
import com.nat.storyappawal.data.repository.StoryRepository
import com.nat.storyappawal.data.pref.UserPreference
import com.nat.storyappawal.utils.dataStore

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return StoryRepository.getInstance(apiService, pref)
    }
}