package com.zzr.jetpacktest

class SimpleData<out T>(val data: T?) {

    fun get(): T? {
        return data
    }
}