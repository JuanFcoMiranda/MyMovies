package com.jfma75.mymovies.server

import android.os.Parcel
import kotlinx.android.parcel.Parceler

object RatingParceler : Parceler<Rating> {
    override fun create(parcel: Parcel) =
        Rating(parcel.readString() ?: "", parcel.readString() ?: "")

    override fun Rating.write(parcel: Parcel, flags: Int) {
        parcel.writeString(value)
        parcel.writeString(source)
    }
}