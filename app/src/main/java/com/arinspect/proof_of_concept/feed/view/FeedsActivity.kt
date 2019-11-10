package com.arinspect.proof_of_concept.feed.view

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arinspect.proof_of_concept.R
import com.arinspect.proof_of_concept.application.ProofConceptApplication.Companion.isNetworkConnected
import com.arinspect.proof_of_concept.base.AbstractBaseActivity
import com.arinspect.proof_of_concept.base.absActivityBuilder
import com.arinspect.proof_of_concept.util.gone
import com.arinspect.proof_of_concept.util.show

/**
 * [FeedsActivity] : FeedActivity class provide load feed record. Set feed list into recyclerview
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */
class FeedsActivity : AbstractBaseActivity() {

    companion object {
        const val EXTRA_FLAG_CALL_API = "makeApiCall"
    }

    /**
     * Create Viewmodel object using lazy
     */
    private val feedsDetailViewModel by lazy {
        ViewModelProviders.of(this)[FeedsViewModel::class.java]
    }

    /**
     * Create object for FeedActivityBinder
     */
    private val feedActivityBinder by lazy {
        return@lazy FeedActivityBinder()
    }

    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_feed
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        setupRefreshCallbacks()
        observeFeeds()
        if (savedInstanceState == null) {
            getFeeds()
        } else {
            if (savedInstanceState.keySet().contains(EXTRA_FLAG_CALL_API)
                && savedInstanceState.getBoolean(EXTRA_FLAG_CALL_API, false)
            )
                getFeeds()
        }
    }

    /**
     * Method to handle swipe refresh callback
     */
    private fun setupRefreshCallbacks() {
        feedActivityBinder.onRefreshCallback = {
            if (isNetworkConnected())
                feedsDetailViewModel.refreshFeeds()
            else {
                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
                feedActivityBinder.showSwipeProgress(false)
            }
        }
    }


    /**
     * get feedlist from API or localdatabase
     */
    private fun getFeeds() {
        feedsDetailViewModel.getFeeds()
    }

    /**
     * Method provide observer live data to get updated data whenever change
     */
    private fun observeFeeds() {
        feedsDetailViewModel.feedsLiveData.observe(this, Observer {
            when (resources?.configuration?.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    feedActivityBinder.setRecyclerLayoutManager(
                        StaggeredGridLayoutManager(
                            2,
                            StaggeredGridLayoutManager.VERTICAL
                        )
                    )
                }
                else -> {
                    feedActivityBinder.setRecyclerLayoutManager(
                        LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL, false
                        )
                    )
                }
            }
            feedActivityBinder.swipeProgress.set(Pair(first = true, second = false))
            title = this.sharedPreference?.getString("title", "")
            if (it?.isNullOrEmpty() == true) {
                feedActivityBinder.binding?.tvNodata?.show()
            } else {
                feedActivityBinder.binding?.tvNodata?.gone()
                feedActivityBinder.feedsAdapter?.addAllItems(it.toMutableList())
            }
        })
    }
}
