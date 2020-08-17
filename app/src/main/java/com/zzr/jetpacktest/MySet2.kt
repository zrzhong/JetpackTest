package com.zzr.jetpacktest

class MySet2<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {
    fun helloWorld() = println("Hello World1")

    override fun isEmpty() = false

}