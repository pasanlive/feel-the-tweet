package com.pasanlive.feelthetweet.model.twitter

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

data class Tweet(
        var created_at: String,
        var id: String,
        var text: String,
        var user: User
)

