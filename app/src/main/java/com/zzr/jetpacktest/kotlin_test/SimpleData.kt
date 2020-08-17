package com.zzr.jetpacktest.kotlin_test

class SimpleData<out T>(val data: T?) {

    fun get(): T? {
        return data
    }
}