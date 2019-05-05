package com.pasanlive.feelthetweet.core

import android.support.test.runner.AndroidJUnit4
import android.util.Base64
import com.pasanlive.feelthetweet.model.twitter.TwitterToken
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by pasan on 2019-05-01.
 */
@RunWith(AndroidJUnit4::class)
class ApiServiceTest{
    private var twitterBearerToken: TwitterToken? = null

    @Test
    fun authToken() {
        getTwitterAuthToken()

        assertNotNull(twitterBearerToken)
        assertNotNull(twitterBearerToken!!.accessToken)
        assertEquals(twitterBearerToken!!.tokenType, "bearer")
        assertTrue(twitterBearerToken!!.accessToken.length > 10)
    }

    @Test
    fun searchTweets() {
        if (twitterBearerToken == null) {
            getTwitterAuthToken()
        }
        val twitterApiService = ApiService.createTwitterApiService()
        val tweetsResponse = twitterApiService.search(
            "nasa",
            twitterBearerToken!!.tokenType + " " + twitterBearerToken!!.accessToken
        ).execute()

        assertNotNull("tweetsResponse cannot be null", tweetsResponse)
        assertTrue(tweetsResponse.isSuccessful)
        assertNotNull("tweetsResponse body cannot be null", tweetsResponse.body())
        assertNotNull(tweetsResponse.body()!!.statuses)
        assertTrue(tweetsResponse.body()!!.statuses.isNotEmpty())


    }

    private fun getTwitterAuthToken() {
        val twitterApiService = ApiService.createTwitterApiService()

        val twitterTokenResponse = twitterApiService.getToken(
            "Basic " + Base64.encodeToString(
                (Config.TWITTER_API_KEY + ":" + Config.TWITTER_API_SECRET).toByteArray(charset("UTF-8")),
                Base64.NO_WRAP
            ),
            "client_credentials"
        ).execute()

        assertNotNull(twitterTokenResponse)
        assertTrue(twitterTokenResponse.isSuccessful)
        assertNotNull(twitterTokenResponse.body())
        twitterBearerToken = twitterTokenResponse.body()
    }
}
