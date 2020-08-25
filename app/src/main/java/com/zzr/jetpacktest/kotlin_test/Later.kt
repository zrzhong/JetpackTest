package com.zzr.jetpacktest.kotlin_test

import kotlin.reflect.KProperty

fun <T> later(block: () -> T) = Later(block)
class Later<T>(val block: () -> T) {

    var value: Any? = null
    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}