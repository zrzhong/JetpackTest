package com.zzr.jetpacktest.kotlin_test

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/27
 */
class TrimDelegate : ReadWriteProperty<Any?, String> {

    private var trimmedValue: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return trimmedValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        trimmedValue = value.trim()
    }
}