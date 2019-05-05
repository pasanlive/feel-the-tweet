package com.pasanlive.feelthetweet.model.twitter

import com.google.gson.annotations.SerializedName

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

data class TwitterToken(
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("access_token") val accessToken: String
)
