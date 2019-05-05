package com.pasanlive.feelthetweet.core

import com.pasanlive.feelthetweet.model.twitter.TweetSearchResult
import com.pasanlive.feelthetweet.model.twitter.TwitterToken
import retrofit2.Call
import retrofit2.http.*


/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

interface TwitterApiService {
    @GET("1.1/search/tweets.json")
    fun search(
            @Query("q") twitterUserName: String,
            @Header("authorization") apiKey: String
    ): Call<TweetSearchResult>

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun getToken(
            @Header("Authorization") authorization: String,
            @Field("grant_type") grantType: String
    ): Call<TwitterToken>
}
