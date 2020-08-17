package com.zzr.jetpacktest.kotlin_test

infix fun String.beginWith(prefix: String) = startsWith(prefix)

infix fun <T> Collection<T>.has(element: T) = contains(element)

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)

fun main() {
    val flag = "hello infix".beginWith("hello")
    println("flag=$flag")
    if ("infix hello" beginWith "infix") {
        println("infix hello")
    }

    val list = listOf<String>("aaa", "bbb", "ccc", "ddd", "eee")
    if (list.contains("aaa")) {
        println("list has aaa")
    }
    if (list has "bbb") {
        println("list has bbb")
    }
}