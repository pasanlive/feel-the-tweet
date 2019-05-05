package com.pasanlive.feelthetweet.core

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.language.v1.AnalyzeSentimentResponse
import com.google.cloud.language.v1.Document
import com.google.cloud.language.v1.LanguageServiceClient
import com.google.cloud.language.v1.LanguageServiceSettings
import com.pasanlive.feelthetweet.FeelTheTweetApplication
import java.io.IOException


/**
 * by: Pasan Buddhika Weerathunaga
 * Email: me@pasanlive.com
 */

class GoogleNaturalLanguageApiService {
    fun analyseSentiment(content: String): AnalyzeSentimentResponse {
        lateinit var mLanguageClient: LanguageServiceClient
        try {
            mLanguageClient = LanguageServiceClient.create(
                    LanguageServiceSettings.newBuilder()
                            .setCredentialsProvider {
                                GoogleCredentials.fromStream(
                                        FeelTheTweetApplication.getInstance()
                                                .resources
                                                .openRawResource(com.pasanlive.feelthetweet.R.raw.credential)
                                )
                            }
                            .build()
            )
        } catch (e: IOException) {
            throw IllegalStateException("Unable to create a language client", e)
        }

        val doc = Document.newBuilder()
                .setContent(content)
                .setType(Document.Type.PLAIN_TEXT)
                .build()
        return mLanguageClient.analyzeSentiment(doc)
    }
}
