package com.arinspect.proof_of_concept.feed.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto
import com.arinspect.proof_of_concept.room.ProofOfConceptDbContract

/**
 * [FeedDao] : Interface provided to expose some method related to CRUD operations on database for [FeedEntityDto]
 *
 * @author Mitesh Vanaliya
 * @version 1.0.0
 * @since 11/09/2019
 */

@Dao
interface FeedDao {

    /**
     *  insert all feeds to the database
     *  @param detailList as [List] of [FeedEntityDto]s object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllFeeds(detailList: List<FeedEntityDto>)

    /**
     *  returns list of feeds [FeedEntityDto]
     */
    @Query("SELECT * FROM ${ProofOfConceptDbContract.FEED_DETAIL_TABLE_NAME}")
    fun getAllFeeds(): List<FeedEntityDto>

    /**
     *  returns count (no of rows) from the table [ConceptDataContract.FEED_DETAIL_TABLE_NAME]
     */
    @Query("SELECT COUNT(${ProofOfConceptDbContract.FEED_ID}) FROM ${ProofOfConceptDbContract.FEED_DETAIL_TABLE_NAME}")
    fun getFeedsCount(): Long

    /**
     * Clear rows from the table [ConceptDataContract.FEED_DETAIL_TABLE_NAME]
     */
    @Query("DELETE FROM ${ProofOfConceptDbContract.FEED_DETAIL_TABLE_NAME}")
    fun clearFeedsTable()

}