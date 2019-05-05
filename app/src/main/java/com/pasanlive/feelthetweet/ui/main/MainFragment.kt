package com.pasanlive.feelthetweet.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.pasanlive.feelthetweet.core.TwitterRepository
import com.pasanlive.feelthetweet.model.twitter.TweetSearchResult
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class MainFragment : Fragment() {
    private lateinit var tweetsRecyclerView: RecyclerView
    private var tweetListAdapter: TweetListAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(com.pasanlive.feelthetweet.R.layout.main_fragment, container, false)

        rootView.search_button.setOnClickListener {
            searchTweets(twitter_username_edit_text.text.toString())
        }

        rootView.twitter_username_edit_text.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTweets(twitter_username_edit_text.text.toString())
                    return true
                }
                return false
            }
        })

        tweetsRecyclerView = rootView.findViewById(com.pasanlive.feelthetweet.R.id.tweets_list_view)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        tweetsRecyclerView.layoutManager = linearLayoutManager

        return rootView
    }

    private fun searchTweets(query: String) {
        TwitterRepository.getInstance().getTweets(query).observeForever { t: TweetSearchResult? ->
            t?.let {
                tweetListAdapter = TweetListAdapter(t.statuses)
                tweetsRecyclerView.adapter = tweetListAdapter
            }
        }
    }
}
