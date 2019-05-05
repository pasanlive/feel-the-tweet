package com.pasanlive.feelthetweet.core

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.cloud.language.v1.AnalyzeSentimentResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class GoogleNaturalLanguageRepository {
    companion object {
        private var instance: GoogleNaturalLanguageRepository? = null

        fun getInstance(): GoogleNaturalLanguageRepository {
            if (instance == null)
                instance = GoogleNaturalLanguageRepository()
            return instance as GoogleNaturalLanguageRepository
        }
    }

    fun getSentimentAnalysis(content: String): LiveData<AnalyzeSentimentResponse> {
        val data: MutableLiveData<AnalyzeSentimentResponse> = MutableLiveData()
        GlobalScope.launch {
            data.postValue(ApiService.createGoogleNaturalLanguageApiService().analyseSentiment(content))
        }
        return data
    }
}
