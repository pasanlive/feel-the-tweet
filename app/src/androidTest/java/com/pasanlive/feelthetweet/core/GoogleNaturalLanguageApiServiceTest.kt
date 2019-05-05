package com.pasanlive.feelthetweet.core

import android.util.Log
import org.junit.Test

/**
 * Created by pasan on 2019-05-05.
 */
class GoogleNaturalLanguageApiServiceTest {

    @Test
    fun analyseSentiment() {
        val googleNaturalLanguageApiService = ApiService.createGoogleNaturalLanguageApiService()
        val response = googleNaturalLanguageApiService.analyseSentiment("Yeah it's cool")
        Log.d("Res", "response : $response")
    }
}
