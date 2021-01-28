package com.zzr.jetpacktest.kotlin_test


fun main() {
    val num1 = 100
    val num2 = 80
//    val result1 = num1AndNum2(num1, num2, ::plus)
//    val result2 = num1AndNum2(num1, num2, ::minus)
    val result1 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 + n2
    }
    val result2 = num1AndNum2(num1, num2) { n1, n2 ->
        n1 - n2
    }
    println("result1 is $result1")
    println("result2 is $result2")

    val list = listOf("Apple", "Banana", "orange", "Pear", "Grape")
    //类似apply的功能
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())

    val array = Array(3) { it->it.inc()}
}

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

//拓展函数
//inline fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
//    block()
//    return this
//}

inline fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}
