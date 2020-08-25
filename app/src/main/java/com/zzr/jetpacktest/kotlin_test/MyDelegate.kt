package com.zzr.jetpacktest.kotlin_test

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MyDelegate : ReadWriteProperty<MyClass, String> {
    private var myValue: String? = null

    override operator fun getValue(thisRef: MyClass, property: KProperty<*>): String {
//        myValue = "property is ${property.name},value is zzr"
        return "property is ${property.name},value is $myValue"
    }

    override operator fun setValue(thisRef: MyClass, property: KProperty<*>, value: String) {
        myValue = value
    }
}