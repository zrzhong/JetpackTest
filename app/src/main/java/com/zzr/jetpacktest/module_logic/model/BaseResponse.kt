package com.zzr.jetpacktest.module_logic.model

data class BaseResponse<T>(
    val errorCode: Int = 0,
    val errorMsg: String? = null,
    var data: T
)
