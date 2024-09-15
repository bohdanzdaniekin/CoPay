package utils.extensions

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

expect fun String.isDigitsOnly(): Boolean

@OptIn(ExperimentalUuidApi::class)
fun randomUUID(): String = Uuid.random().toString()

