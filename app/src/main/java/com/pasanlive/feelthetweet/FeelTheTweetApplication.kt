package com.pasanlive.feelthetweet

import android.app.Application
import com.pasanlive.feelthetweet.core.TwitterRepository

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class FeelTheTweetApplication : Application() {
    companion object {
        private lateinit var instance: FeelTheTweetApplication

        fun getInstance(): FeelTheTweetApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        TwitterRepository.getInstance().retrieveApiAuthKey()

    }
}
