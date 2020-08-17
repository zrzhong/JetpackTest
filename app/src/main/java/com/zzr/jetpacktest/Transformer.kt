package com.zzr.jetpacktest

interface Transformer<in T> {
    fun transform(t: T): String
}