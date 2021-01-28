package com.zzr.jetpacktest.kotlin_test

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/29
 */

object ago

object fromNow

val Int.days: Period
    get() = Period.ofDays(this)


infix fun Int.days(ago: ago) = baseDate().minusDays(toLong())

private fun baseDate() = LocalDate.now()

private fun baseTime() = LocalDateTime.now()
