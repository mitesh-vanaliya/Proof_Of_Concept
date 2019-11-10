package com.arinspect.proof_of_concept.feed.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arinspect.proof_of_concept.feed.data.FeedsDetailRepository
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto

/**
 * [FeedsViewModel] : FeedsViewModel class provide livedata object and repository object for API call
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */
class FeedsViewModel : ViewModel() {


    val feedsLiveData: LiveData<List<FeedEntityDto?>> = MutableLiveData()

    /**
     * Get Feed record
     */
    fun getFeeds() {
        feedsDetailRepository.getFeeds {
            (feedsLiveData as? MutableLiveData<*>)?.value = it
        }
    }

    /**
     * In this method clearAll localDatabase and get updated feed
     */
    fun refreshFeeds(){
        feedsDetailRepository.clearAllLocalDataBase()
        getFeeds()
    }

    /**
     * Create feedDetailRepository object
     */
    private val feedsDetailRepository by lazy {
        return@lazy FeedsDetailRepository.getInstance()
    }

}