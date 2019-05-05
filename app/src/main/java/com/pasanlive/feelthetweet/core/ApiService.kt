package com.pasanlive.feelthetweet.core

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class ApiService {
    companion object {
        fun createTwitterApiService(): TwitterApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.TWITTER_BASE_URL)
                    .build()

            return retrofit.create(TwitterApiService::class.java)
        }

        fun createGoogleNaturalLanguageApiService(): GoogleNaturalLanguageApiService {
            return GoogleNaturalLanguageApiService()
        }
    }
}
