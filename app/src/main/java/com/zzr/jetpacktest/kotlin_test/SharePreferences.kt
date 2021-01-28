package com.zzr.jetpacktest.kotlin_test

import android.content.SharedPreferences

//拓展函数 函数参数类型
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit): Unit {
    val editor = edit()
    editor.block()
    editor.apply()
}
