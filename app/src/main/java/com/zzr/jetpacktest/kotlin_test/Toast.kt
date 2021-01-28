package com.zzr.jetpacktest.kotlin_test

import android.content.Context
import android.widget.Toast

fun main() {
    println(intToString(0))
}

fun intToString(num: Int): String {
    if (num == 0) throw RuntimeException("num can not be zero!")

    return num.toString()
}

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}