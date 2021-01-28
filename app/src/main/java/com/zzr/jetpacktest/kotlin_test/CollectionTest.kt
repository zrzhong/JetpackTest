package com.zzr.jetpacktest.kotlin_test

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/25
 */

fun main() {
//    listToArray()
//    operation()
//    sort()
//    mapOperation()
//    filter()
//    operation2()
    statistics()
}

fun listToArray() {
    val data = listOf(1, 2, 3, 4, 5)
    val array = data.toIntArray()
    println(data.javaClass.toString())
    println(array.javaClass.toString())
    println(array[0])
}

fun arrayToList() {
    val array = arrayOf(1, 3, 5, 7, 9)
    val list = array.toMutableList()
}

fun operation() {
    val list = mutableListOf("1", "3", "5", "7", "9")
    val result = list.contains("7")
    println("contains==$result")
    val a = list.elementAt(0)
    val b = list.elementAtOrElse(5) { 1024 }
    val c = list.elementAtOrNull(6)
    println("a==$a")
    println("b==$b")
    println("c==$c")

//    val first = list.first()
    val first = list.firstOrNull()
//    val first2 = list.first { it == "9" }
    val first2 = list.firstOrNull { it == "6" }
    println("first==$first")
    println("first2==$first2")
    //last同理


}

fun sort() {
    val list1 = listOf(-1, -3, 1, 3, 5, 6, 7, 2, 4, 10, 9, 8)

// 反序
    println(list1.reversed())

// 升序
    println(list1.sorted())

// 根据条件升序，即把不满足条件的放在前面，满足条件的放在后面
    println(list1.sortedBy { it % 2 == 0 })

// 降序
    println(list1.sortedDescending())

// 根据条件降序，和`sortedBy{}`相反
    println(list1.sortedByDescending { it % 2 == 0 })
}

fun mapOperation() {
    val list1 = listOf("kotlin", "Android", "Java", "PHP", "JavaScript")
    val newList = list1.flatMap {
        listOf(it, "new-".plus(it))
    }
    println(newList)
    //Map<K, List<T>>
    val map = list1.groupBy {
        if (it.startsWith("Java")) "java" else "other"
    }
    println(map)

}

fun filter() {
    val list1 = listOf(-1, -3, 1, 3, 5, 6, 7, 2, 4, 10, 9, 8)
    val list2 = listOf(1, 3, 4, 5, null, 6, null, 10)
    val list3 = listOf(1, 1, 5, 2, 2, 6, 3, 3, 7, 4, 4, 8)


    println("  ------   filter -------")
    println(list1.filter { it > 1 })
    println(list1.filterIndexed { index, result ->
        index < 5 && result > 3
    })
    println(list1.filterNot { it > 1 })
    println(list2.filterNotNull())

    println("  ------   take -------")
    println(list1.take(5))
    println(list1.takeWhile { it < 5 })
    println(list1.takeLast(5))
    println(list1.takeLastWhile { it > 5 })

    println("  ------   drop -------")
    println(list1.drop(5))
    println(list1.dropWhile { it < 5 })
    println(list1.dropLast(5))
    println(list1.dropLastWhile { it > 5 })

    println("  ------   distinct -------")
    println(list3.distinct())
    println(list3.distinctBy { it + 2 })

    println("  ------   slice -------")
    println(list1.slice(listOf(1, 3, 5, 7)))
    println(list1.slice(IntRange(1, 5)))
}

fun operation2() {

    val list1 = listOf(1, 2, 3, 4)
    val list2 = listOf("kotlin", "Android", "Java", "PHP", "JavaScript")

// plus() 和 `+`一样
    println(list1.plus(list2))
    println(list1 + list2)

// zip List<Pair>
    println(list1.zip(list2))
    // 组成的新集合由元素少的原集合决定
    println(list1.zip(list2) { it1, it2 ->
//        it1.toString().plus("-").plus(it2)
        it2.plus("-".plus(it1))
    })

// unzip
    val newList = listOf(Pair(1, "Kotlin"), Pair(2, "Android"), Pair(3, "Java"), Pair(4, "PHP"))
    val pair: Pair<List<Int>, List<String>> = newList.unzip()
    println("pair.first==${pair.first} pair.second==${pair.second}")

// partition
    val pair2: Pair<List<String>, List<String>> = list2.partition {
        it.startsWith("Ja")
    }
    println("pair2.first==${pair2.first} pair2.second==${pair2.second}")
}

fun statistics() {
    val list1 = listOf(1, 2, 3, 4, 5)

    println("  ------   any -------")
    println(list1.any())
    println(list1.any { it > 10 })

    println("  ------   all -------")
    println(list1.all { it > 2 })

    println("  ------   none -------")
    println(list1.none())
    println(list1.none { it > 2 })

    println("  ------   max -------")
    println(list1.maxOrNull())
    println(list1.maxByOrNull { it + 2 })

    println("  ------   min -------")
    println(list1.minOrNull())        // 返回集合中最小的元素
    println(list1.minByOrNull { it + 2 })

    println("  ------   sum -------")
    println(list1.sum())
    println(list1.sumBy { it + 2 })
    println(list1.sumByDouble { it.toDouble() })

    println(" ------  average -----")
    println(list1.average())

    println("  ------   reduce  -------")
    println(list1.reduce { result, next -> result + next })
    println(list1.reduceIndexed { index, result, next ->
        index + result + next
    })
    println(list1.reduceRight { result, next -> result + next })
    println(list1.reduceRightIndexed { index, result, next ->
        index + result + next
    })

    println("  ------   fold  -------")
    println(list1.fold(3) { result, next -> result + next })
    println(list1.foldIndexed(3) { index, result, next ->
        index + result + next
    })
    println(list1.foldRight(3) { result, next -> result + next })
    println(list1.foldRightIndexed(3) { index, result, next ->
        index + result + next
    })

    println(list1.count {
        it % 2 != 0
    })
}
