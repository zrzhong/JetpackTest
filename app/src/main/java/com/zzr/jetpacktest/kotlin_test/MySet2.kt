package com.zzr.jetpacktest.kotlin_test

class MySet2<T>(private val helperSet: HashSet<T>) : Set<T> by helperSet {
    fun helloWorld() = println("Hello World1")

    override fun isEmpty() = false

}