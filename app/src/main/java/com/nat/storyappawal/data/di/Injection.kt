package com.nat.storyappawal.data.di

import android.content.Context
import com.nat.storyappawal.data.api.ApiConfig
import com.nat.storyappawal.data.repository.StoryRepository
import com.nat.storyappawal.utils.UserPreference
import com.nat.storyappawal.utils.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUser().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoryRepository.getInstance(apiService, pref)
    }
}