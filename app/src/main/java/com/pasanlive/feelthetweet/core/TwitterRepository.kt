package com.pasanlive.feelthetweet.core

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Base64
import com.pasanlive.feelthetweet.model.twitter.TweetSearchResult
import com.pasanlive.feelthetweet.model.twitter.TwitterToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class TwitterRepository {

    companion object {
        private var instance: TwitterRepository? = null

        fun getInstance(): TwitterRepository {
            if (instance == null)
                instance = TwitterRepository()
            return instance as TwitterRepository
        }
    }

    private val twitterToken: MutableLiveData<TwitterToken> = MutableLiveData()

    fun getTweets(twitterUserName: String): LiveData<TweetSearchResult> {
        val data: MutableLiveData<TweetSearchResult> = MutableLiveData()

        ApiService.createTwitterApiService().search(
                "From:$twitterUserName",
                twitterToken.value!!.tokenType + " " + twitterToken.value!!.accessToken
        ).enqueue(object : Callback<TweetSearchResult> {
            override fun onFailure(call: Call<TweetSearchResult>, t: Throwable) {

            }

            override fun onResponse(call: Call<TweetSearchResult>, response: Response<TweetSearchResult>) {
                data.value = response.body()
            }
        })
        return data
    }


    fun retrieveApiAuthKey() {
        ApiService.createTwitterApiService().getToken(
                "Basic " + Base64.encodeToString(
                        (Config.TWITTER_API_KEY + ":" + Config.TWITTER_API_SECRET).toByteArray(charset("UTF-8")),
                        Base64.NO_WRAP
                ),
                "client_credentials"
        ).enqueue(object : Callback<TwitterToken> {
            override fun onFailure(call: Call<TwitterToken>, t: Throwable) {

            }

            override fun onResponse(call: Call<TwitterToken>, response: Response<TwitterToken>) {
                twitterToken.value = response.body()
            }
        })
    }

}
