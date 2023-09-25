package com.mr.nemo.dragonfly.domain.entity.auth.signup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoItem(
    val title: String,
    val subtitle: String
) : Parcelable
