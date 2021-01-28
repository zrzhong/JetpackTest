package com.zzr.jetpacktest.kotlin_test

import java.util.*

/**
 * @Author zzr
 * @Desc
 * @Date 2020/9/27
 */
class Example {
    companion object{
        private object Args{

        }
    }

    var param: String = ""
        set(value) {
            field = value.trim()
        }
        get() = field.capitalize()

    var param2: String by TrimDelegate()

    private val delegate = TrimDelegate()

    var param3: String
        get() = delegate.getValue(this, ::param3)
        set(value) {
            delegate.setValue(this, ::param3, value)
        }
}