package com.arinspect.proof_of_concept.feed.data

import com.arinspect.proof_of_concept.api.RetrofitSingleton
import com.arinspect.proof_of_concept.api.enqueueOn
import com.arinspect.proof_of_concept.api.failure
import com.arinspect.proof_of_concept.api.success
import com.arinspect.proof_of_concept.application.ProofConceptApplication
import com.arinspect.proof_of_concept.feed.model.FeedListDto

/**
 * RemoteFeedsDataSource :
 *
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */
class RemoteFeedsDataSource {

    object HOLDER {
        val instance = RemoteFeedsDataSource()
    }

    companion object {
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }

    private val mApiService by lazy {
        return@lazy RetrofitSingleton.getInstance().provideApiService()
    }

    fun getFeeds(onFeedsCallback: (FeedListDto?) -> Unit) {
        if (ProofConceptApplication.isNetworkConnected()) {

            mApiService.getFeedsDetails().enqueueOn().success { _, response ->
                when {
                    response.isSuccessful && response.code() == 200 -> {
                        onFeedsCallback(response.body())
                    }
                    else -> {
                        onFeedsCallback(response.body())
                    }
                }
            } failure { _, t ->
                onFeedsCallback(null)
            }
        }
    }
}
