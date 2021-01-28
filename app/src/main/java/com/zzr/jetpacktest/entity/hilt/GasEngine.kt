package com.zzr.jetpacktest.entity.hilt

import javax.inject.Inject

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/26
 */
class GasEngine @Inject constructor() : Engine {

    override fun start() {
        println("GasEngine is start")
    }

    override fun shutDown() {
        println("GasEngine is shutDown")
    }
}