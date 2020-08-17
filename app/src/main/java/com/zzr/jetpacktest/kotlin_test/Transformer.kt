package com.zzr.jetpacktest.kotlin_test

interface Transformer<in T> {
    fun transform(t: T): String
}