package com.fimo.aidentist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    var name: String,
    var category: String,
    var rating: String,
    var schedule: String,
    var avatar: Int,
): Parcelable
