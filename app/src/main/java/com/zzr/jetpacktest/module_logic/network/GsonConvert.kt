package com.zzr.jetpacktest.module_logic.network

import com.drake.net.convert.DefaultConvert
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/30
 */
class GsonConvert : DefaultConvert(code = "code", message = "msg", success = "200") {
    val gson = GsonBuilder().serializeNulls().create()
    override fun <S> String.parseBody(succeed: Type): S? {
        return gson.fromJson(this, succeed)
    }
}