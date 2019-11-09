package com.arinspect.proof_of_concept.room

import com.arinspect.proof_of_concept.BuildConfig

/**
 * [ProofOfConceptDbContract] : Data contract class that provides constant data related to [ProofOfConceptDb] & it's related models.
 *
 * @author Mitesh Vanaliya
 * @since 11/09/2019
 * @version 1.0.0
 */
class ProofOfConceptDbContract {
    companion object {
        // Tag for logcat.
        const val TAG = "ProofOfConceptDbContract"
        /**
         * defines database version for [ProofOfConceptDb]
         */
        const val DB_VERSION = 1
        /**
         * defines new database version for [ProofOfConceptDb]
         */
        const val NEW_DB_VERSION = 1
        /**
         * defines database name for [ProofOfConceptDb]
         */
        const val DB_NAME = BuildConfig.DATABASE_NAME
        /**
         * defines Association table name for [AssociationDto]
         */
        const val FEED_DETAIL_TABLE_NAME = "Feed_Detail"
        /**
         * defines column name '_id' for [FeedEntityDto]
         */
        const val FEED_ID = "_id"

        /**
         * defines column name 'title' for [FeedEntityDto]
         */
        const val FEED_TITLE = "title"

        /**
         * defines column name 'description' for [FeedEntityDto]
         */
        const val FEED_DESCRIPTION = "description"

        /**
         * defines column name 'imageurl' for [FeedEntityDto]
         */
        const val FEED_URL = "imageurl"

    }
}
