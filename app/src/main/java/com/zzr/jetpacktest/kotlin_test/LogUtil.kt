package com.zzr.jetpacktest.kotlin_test

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/24
 */
object LogUtil {

    private val dateFormat = SimpleDateFormat("HH:mm:ss:SSS", Locale.CHINA)

    private val now = {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    fun log(msg: Any?) {
        println("${now()} [${Thread.currentThread().name}] $msg")
    }
}