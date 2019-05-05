package com.pasanlive.feelthetweet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pasanlive.feelthetweet.ui.main.MainFragment

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
