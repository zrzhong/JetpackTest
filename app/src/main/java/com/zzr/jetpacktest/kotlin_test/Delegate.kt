package com.zzr.jetpacktest.kotlin_test

import kotlin.reflect.KProperty

class Delegate {
    private var proValue: Any? = null

    operator fun getValue(myClass: MyClass, prop: KProperty<*>): Any? {
        return proValue
    }

    operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
        proValue = value
    }
}