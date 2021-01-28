package com.zzr.mylibrary.widget

/**
 * @Author zzr
 * @Desc
 * @Date 2021/1/13
 */
internal object Helper {

    fun checkMaxCount(maxLine: Int) {
        if (maxLine < 1) {
            throw IllegalArgumentException("maxLine = $maxLine must > 1")
        }
    }
}