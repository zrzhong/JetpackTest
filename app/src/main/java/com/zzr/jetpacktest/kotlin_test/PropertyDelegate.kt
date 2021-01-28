package com.zzr.jetpacktest.kotlin_test

import android.content.SharedPreferences
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/29
 */
fun SharedPreferences.string(
    defaultValue: String = "",
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String> {
    return object : ReadWriteProperty<Any, String> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            edit().putString(key(property), value).apply()
        }

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return getString(key(property), defaultValue) ?: defaultValue
        }
    }
}

fun TextView.text(): ReadWriteProperty<Any, String> {
    return object : ReadWriteProperty<Any, String> {

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            text = value
        }

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return text.toString()
        }
    }
}

fun View.isVisible(keepBounds: Boolean): ReadWriteProperty<Any, Boolean> {

    return object : ReadWriteProperty<Any, Boolean> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
            visibility = when {
                value -> View.VISIBLE
                keepBounds -> View.INVISIBLE
                else -> View.GONE
            }
        }

        override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
            return visibility == View.VISIBLE
        }
    }
}


