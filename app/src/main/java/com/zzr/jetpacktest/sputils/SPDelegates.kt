package com.zzr.jetpacktest.sputils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SPDelegates<T>(
    private val key: String,
    private val default: T,
    private val isCommit: Boolean = false
) :
    ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return SPUtils.getValue(key, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        SPUtils.putValue(key, value, isCommit)
    }
}