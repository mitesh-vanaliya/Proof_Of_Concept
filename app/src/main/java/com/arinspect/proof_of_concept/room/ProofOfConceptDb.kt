package com.arinspect.proof_of_concept.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arinspect.proof_of_concept.application.ProofConceptApplication
import com.arinspect.proof_of_concept.feed.data.FeedDao
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto

/**
 * @author Mitesh Vanaliya
 * @since 10/14/2019
 * @version 1.0.0
 */
@Database(
    entities = [FeedEntityDto::class],
    version = ProofOfConceptDbContract.DB_VERSION
)

abstract class ProofOfConceptDb : RoomDatabase() {
    private object Holder {
        val INSTANCE = ProofConceptApplication.context.applicationContext?.let {
            Room.databaseBuilder(
                it,
                ProofOfConceptDb::class.java,
                ProofOfConceptDbContract.DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

        }
    }

    abstract val feedDao: FeedDao

    companion object {
        // Tag for logcat.
        const val TAG = "ProofOfConceptDb"

        /**
         * Singleton instance of [ProofOfConceptDb]
         */
        val instance: ProofOfConceptDb? by lazy { Holder.INSTANCE }
    }
}