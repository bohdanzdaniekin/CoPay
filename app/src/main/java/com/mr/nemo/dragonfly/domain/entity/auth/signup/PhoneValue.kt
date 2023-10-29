package com.mr.nemo.dragonfly.domain.entity.auth.signup

data class PhoneValue(
    val code: String = "",
    val number: String = ""
) {

    val fullNumber: String
        get() = code + SEPARATOR + number

    companion object {

        const val SEPARATOR = " "
    }
}
