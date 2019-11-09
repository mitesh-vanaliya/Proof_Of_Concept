package com.arinspect.proof_of_concept.feed.model

import android.os.Parcel
import android.os.Parcelable
import com.arinspect.proof_of_concept.feed.model.FeedEntityDto
import com.google.gson.annotations.SerializedName

data class FeedListDto(
    @SerializedName("title")
    var feedTitle: String? = null,
    @SerializedName("rows")
    var feedsList: List<FeedEntityDto>? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        feedTitle = parcel.readString(),
        feedsList = parcel.createTypedArrayList(FeedEntityDto.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(feedTitle)
        parcel.writeTypedList(feedsList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedListDto> {
        override fun createFromParcel(parcel: Parcel): FeedListDto {
            return FeedListDto(parcel)
        }

        override fun newArray(size: Int): Array<FeedListDto?> {
            return arrayOfNulls(size)
        }
    }
}