package com.arinspect.proof_of_concept.feed.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arinspect.proof_of_concept.room.ProofOfConceptDbContract
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = ProofOfConceptDbContract.FEED_DETAIL_TABLE_NAME
)
data class FeedEntityDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProofOfConceptDbContract.FEED_ID)
    @SerializedName("id")
    var mId: Long,

    @ColumnInfo(name = ProofOfConceptDbContract.FEED_TITLE)
    @SerializedName("title")
    var mTitle: String?,

    @ColumnInfo(name = ProofOfConceptDbContract.FEED_DESCRIPTION)
    @SerializedName("description")
    var mDescription: String?,

    @ColumnInfo(name = ProofOfConceptDbContract.FEED_URL)
    @SerializedName("imageHref")
    var mImageUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(mId)
        parcel.writeString(mTitle)
        parcel.writeString(mDescription)
        parcel.writeString(mImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedEntityDto> {
        override fun createFromParcel(parcel: Parcel): FeedEntityDto {
            return FeedEntityDto(parcel)
        }

        override fun newArray(size: Int): Array<FeedEntityDto?> {
            return arrayOfNulls(size)
        }
    }
}