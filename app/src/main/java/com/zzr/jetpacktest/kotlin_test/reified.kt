package com.zzr.jetpacktest.kotlin_test

import android.content.Context
import android.content.Intent
import com.zzr.jetpacktest.entity.Person
import com.zzr.jetpacktest.entity.Student

inline fun <reified T> gotoActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

inline fun <reified T> gotoActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

fun main() {
    val student = Student("Tom", 19)
    val data = SimpleData<Student>(student)
//    data.set(student)
    handleSimpleData(data)
    val studentData = data.get()
}

fun handleSimpleData(data: SimpleData<Person>) {
//    val teacher = Teacher("Jack", 35)
//    data.set(teacher)
    val personData = data.get()
}