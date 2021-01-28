package com.zzr.jetpacktest.entity.hilt

import javax.inject.Inject

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/26
 */
class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        println("ElectricEngine is start")
    }

    override fun shutDown() {
        println("ElectricEngine is shutDown")
    }
}