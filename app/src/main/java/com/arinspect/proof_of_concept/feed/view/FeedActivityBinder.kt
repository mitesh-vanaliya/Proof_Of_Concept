package com.arinspect.proof_of_concept.feed.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arinspect.proof_of_concept.BR
import com.arinspect.proof_of_concept.R
import com.arinspect.proof_of_concept.base.AbstractBinding
import com.arinspect.proof_of_concept.databinding.ActivityFeedBinding
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto
import com.arinspect.proof_of_concept.util.BindingRecyclerAdapter
import com.arinspect.proof_of_concept.util.gone
import com.arinspect.proof_of_concept.util.show
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * [FeedActivityBinder] :
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */
class FeedActivityBinder : AbstractBinding<ActivityFeedBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    var feedsAdapter: BindingRecyclerAdapter? = null


    override fun onCreated() {
        binding?.feedActivityBinder = this
        setUpRecyclerView()
    }

    /**
     * Set up recyclerview and holder class
     */
    private fun setUpRecyclerView() {
        feedsAdapter = BindingRecyclerAdapter.Builder()
            .setLayoutResId(R.layout.item_feed_layout)
            .onBindViewHolderCallback { holder, _, adapter ->
                val feed = adapter.list[holder.adapterPosition] as? FeedEntityDto
                holder.binding.setVariable(BR.feedDetail, feed)
            }
            .build()
        binding?.rvFeeds?.adapter = feedsAdapter

    }

    /**
     * Set layoutmanager into recyclerview
     */
    fun setRecyclerLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        binding?.rvFeeds?.layoutManager = layoutManager
    }

    /**
     * Show/hide swipe progress bar
     * @param show boolean type provide swipe enable and disable
     */
    fun showSwipeProgress(show: Boolean = true) {
        swipeProgress.set(Pair(first = true, second = show))
    }

    /**
     *
     */
    override fun onRefresh() {
        onRefreshCallback?.invoke()
    }

    override fun onDestroy() {
        binding?.feedActivityBinder = null
    }

    var onRefreshCallback: (() -> Unit)? = null
    var swipeProgress: ObservableField<Pair<Boolean?, Boolean?>> =
        ObservableField(Pair(first = true, second = false))


    companion object {

        /**
         * Binding adapter method for set image into imageview. use glide for download image from url
         */
        @JvmStatic
        @BindingAdapter(
            "imageUrl",
            requireAll = false
        )
        fun loadServerImage(
            imageView: ImageView?,
            imageUrl: String?
        ) {
            if (!imageUrl.isNullOrEmpty()) {
                imageView?.let { iv ->
                    Glide.with(iv.context)
                        .load(imageUrl)
                        .apply(RequestOptions().override(iv.width, iv.height))
                        .into(iv)
                    iv.show()
                }
            } else {
                imageView?.gone()
            }
        }

        /**
         * Binding Adapter method for setw visibility into view
         */
        @JvmStatic
        @BindingAdapter("setvisibility", requireAll = false)
        fun setVisibility(view: View?, textString: String?) {
            view?.let {
                if (textString.isNullOrEmpty()) {
                    it.gone()
                } else {
                    it.show()
                }
            }
        }
    }
}