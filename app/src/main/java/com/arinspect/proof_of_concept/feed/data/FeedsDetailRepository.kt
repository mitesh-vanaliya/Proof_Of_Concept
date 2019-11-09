package com.arinspect.proof_of_concept.feed.data

import com.arinspect.proof_of_concept.feed.model.FeedEntityDto

/**
 * [FeedsDetailRepository] :
 *
 * Repository class used to provide connection to API end-points or Database for login functionality
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 9/25/2019
 */
class FeedsDetailRepository private constructor() {
    object HOLDER {
        val instance = FeedsDetailRepository()
    }

    companion object {
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }

    private val localDataSource by lazy {
        return@lazy LocalFeedsDataSource.getInstance()
    }

    private val remoteDataSource by lazy {
        return@lazy RemoteFeedsDataSource.getInstance()
    }

    fun getFeeds(onFeedsCallback: (List<FeedEntityDto>?) -> Unit) {
        if (localDataSource.getFeedCounts() == 0L) {
            remoteDataSource.getFeeds {
                localDataSource.addFeeds(it)
                onFeedsCallback(localDataSource.getFeeds())
            }
        } else {
            onFeedsCallback(localDataSource.getFeeds())
        }
    }
}