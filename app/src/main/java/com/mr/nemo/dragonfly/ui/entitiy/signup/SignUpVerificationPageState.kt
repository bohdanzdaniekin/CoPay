package com.mr.nemo.dragonfly.ui.entitiy.signup

import androidx.compose.runtime.Immutable
import com.mr.nemo.dragonfly.domain.entity.auth.signup.PhoneValue
import com.mr.nemo.dragonfly.ui.component.securitycode.SecurityCodeState

@Immutable
data class SignUpVerificationPageState(
    val phone: PhoneValue = PhoneValue(),
    val securityCode: SecurityCodeState = SecurityCodeState(),
    val timer: Timer = Timer.default()
) {

    @Immutable
    data class Timer(
        val minutes: Long,
        val seconds: Long
    ) {

        val totalSeconds = minutes * 60 + seconds

        companion object {

            fun default() = Timer(minutes = 1, seconds = 0)
        }
    }
}
