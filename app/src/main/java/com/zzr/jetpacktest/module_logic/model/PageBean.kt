package com.zzr.jetpacktest.module_logic.model

data class PageBean<T>(
    val curPage: Int,
    val datas: T
)