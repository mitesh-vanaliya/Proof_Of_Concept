package com.arinspect.proof_of_concept.feed.data

import com.arinspect.proof_of_concept.application.ProofConceptApplication
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto
import com.arinspect.proof_of_concept.feed.model.FeedListDto
import com.arinspect.proof_of_concept.room.ProofOfConceptDb
import com.arinspect.proof_of_concept.util.getDefaultPreference
import com.arinspect.proof_of_concept.util.putValue
import com.arinspect.proof_of_concept.util.runInBackground

/**
 * LocalFeedsDataSource :
 *
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */
class LocalFeedsDataSource {
    object HOLDER {
        val instance = LocalFeedsDataSource()
    }

    private val database by lazy {
        return@lazy ProofOfConceptDb.instance
    }

    companion object {
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }

    fun getFeeds(): List<FeedEntityDto>? {
        return database?.feedDao?.runInBackground { getAllFeeds() }
    }

    fun addFeeds(feedsDTO: FeedListDto?) {
        ProofConceptApplication.context.getDefaultPreference()
            .putValue("title", feedsDTO?.feedTitle.toString())
        feedsDTO?.feedsList?.let {
            if (!it.isNullOrEmpty()) {
                clearFeeds()
                database?.feedDao?.runInBackground { addAllFeeds(it) }
            }
        }

    }

    fun getFeedCounts(): Long {
        return database?.feedDao?.runInBackground { getFeedsCount() } ?: 0
    }

    fun clearFeeds() {
        database?.feedDao?.runInBackground { this.clearFeedsTable() }
    }
}

