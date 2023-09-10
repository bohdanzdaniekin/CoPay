package com.mr.nemo.dragonfly.ui.screen.auth.signup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoItem(
    val title: String,
    val subtitle: String
): Parcelable
