package com.arinspect.proof_of_concept.api

import com.arinspect.proof_of_concept.feed.model.FeedListDto
import retrofit2.Call
import retrofit2.http.GET


/**
 * [IAppWebApi] : Interface for API list and related response object
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 10/15/2019
 */

interface IAppWebApi {

    //API for get feed data
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFeedsDetails(): Call<FeedListDto?>
}