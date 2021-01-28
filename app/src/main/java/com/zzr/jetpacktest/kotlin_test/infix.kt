package com.zzr.jetpacktest.kotlin_test

import android.graphics.Color
import com.zzr.jetpacktest.entity.User

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

    /// Java
//    Color first = new Color(255, 0, 255);
//    Color second = new Color(255, 0, 255);
//    assertThat(first.equals(second)).isTrue();
//    assertThat(first == second).isFalse();
    // Kotlin
    val first = User("jack", 20)
    val second = User("jack", 20)
    println(first.equals(second))
    println(first == second)
    println(first === second)

}