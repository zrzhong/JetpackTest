package com.zzr.jetpacktest

import com.zzr.jetpacktest.bean.Study

fun main() {
    /*println("Hello Kotlin!")
    println(getScore("rose"))
    println(getScore2("Tom"))
    val list = listOf("apple", "banana", "orange", "watermelon")
    for (fruit in list) {
        println(fruit)
    }
    val maxFruit = list.maxBy { it.length }
    println(maxFruit)*/

    printParam(str = "hello", num = 120)

    val count = "Abc123xyz!@#".lettersCount()
    println(count)

    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money4.value)

    val helperSet = HashSet<String>()
    helperSet.add("hello")
    helperSet.add("world")
    helperSet.add("kotlin")
    val set = MySet2(helperSet)
    println("set.size==${set.size}")
    println("set contains hello==${set.contains("hello")}")

    val myClass = MyClass()
    myClass.p = "delegate value"
    println("value=${myClass.p}")
}

fun printParam(num: Int = 100, str: String = "hello") {
    println("num is $num,str is $str")
}

fun getTextLength(text: String?): Int {
    if (text != null) {
        return text.length
    }
    return 0
}

fun getTextLength2(text: String?): Int {
    return text?.length ?: 0
}

fun getTextLength3(text: String?) = text?.length ?: 0

fun doStudy(study: Study?) {
    study?.doHomework()
    study?.readBooks()
}

fun doStudy2(study: Study?) {
    study?.let {
        it.doHomework()
        it.readBooks()
    }
}

fun doStudy3(study: Study?) {
    study?.let {
        it.doHomework()
        it.readBooks()
    }
}

fun getScore(name: String) = when (name) {
    "Tom" -> 700
    "jack" -> 60
    "rose" -> 90
    else -> 0
}

fun getScore2(name: String) = when {
    name.startsWith("Tom") -> 80
    name == "jack" -> 60
    else -> 0
}

fun test1() {
    Thread {
        println("")
    }
    Thread { TODO("Not yet implemented") }
}