package com.arinspect.proof_of_concept.feed.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.arinspect.proof_of_concept.R
import com.arinspect.proof_of_concept.base.AbstractBaseActivity
import com.arinspect.proof_of_concept.base.absActivityBuilder

class FeedsActivity : AbstractBaseActivity() {

    private val feedsDetailViewModel by lazy {
        ViewModelProviders.of(this)[FeedsViewModel::class.java]
    }

    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_main
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        Toast.makeText(this, "Feeds Activity class initlization", Toast.LENGTH_LONG).show()
    }

}
