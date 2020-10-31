package com.hjh96.sopt1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData(
    val title : String,
    val subTitle : String,
    val content : String
) : Parcelable