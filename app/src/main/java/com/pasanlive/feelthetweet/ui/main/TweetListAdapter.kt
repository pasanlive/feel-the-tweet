package com.pasanlive.feelthetweet.ui.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.cloud.language.v1.AnalyzeSentimentResponse
import com.pasanlive.feelthetweet.R
import com.pasanlive.feelthetweet.core.GoogleNaturalLanguageRepository
import com.pasanlive.feelthetweet.model.twitter.Tweet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tweet_list_item.view.*

/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class TweetListAdapter(private val tweets: List<Tweet>) : RecyclerView.Adapter<TweetListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.tweet_list_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(vieHolder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        vieHolder.bindTweet(tweet)
    }

    class ViewHolder(private val tweetView: View) : RecyclerView.ViewHolder(tweetView), View.OnClickListener {
        private val mTAG = ViewHolder::class.java.simpleName

        private var tweet: Tweet? = null
        private lateinit var emotionImageView: ImageView
        private lateinit var progressBar: ProgressBar

        init {
            tweetView.setOnClickListener(this)
        }

        fun bindTweet(tweet: Tweet) {
            this.tweet = tweet
            Picasso.get().load(tweet.user.profile_image_url_https).into(tweetView.profile_image)
            tweetView.profile_name.text = tweet.user.name
            tweetView.content.text = tweet.text
            emotionImageView = tweetView.emotion
            progressBar = tweetView.progress
            emotionImageView.visibility = View.GONE
        }

        override fun onClick(v: View?) {
            progressBar.visibility = View.VISIBLE

            GoogleNaturalLanguageRepository.getInstance().getSentimentAnalysis(tweet!!.text)
                    .observeForever { t: AnalyzeSentimentResponse? ->
                        Log.d(mTAG, "TweetSearchResult retrieved : $t")
                        t?.let {
                            progressBar.visibility = View.GONE
                            emotionImageView.setImageResource(getTheFeeling(t.documentSentiment!!.score))
                            emotionImageView.visibility = View.VISIBLE

                            emotionImageView.postDelayed({
                                emotionImageView.visibility = View.GONE
                            }, 2000)
                        }
                    }
        }

        private fun getTheFeeling(score: Float): Int {
            return if (score < -0.25) {
                R.drawable.sad_face
            } else if (score >= -0.25 && score < 0.25) {
                R.drawable.neutral_face
            } else {
                R.drawable.happy_face
            }
        }
    }
}
