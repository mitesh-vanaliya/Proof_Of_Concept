package com.arinspect.proof_of_concept.feed.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arinspect.proof_of_concept.feed.data.FeedsDetailRepository
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto

class FeedsViewModel : ViewModel() {

    val feedsLiveData: LiveData<List<FeedEntityDto?>> = MutableLiveData()

    fun getFeeds() {
        feedsDetailRepository.getFeeds {
            (feedsLiveData as? MutableLiveData<*>)?.value = it
        }
    }

    private val feedsDetailRepository by lazy {
        return@lazy FeedsDetailRepository.getInstance()
    }

}