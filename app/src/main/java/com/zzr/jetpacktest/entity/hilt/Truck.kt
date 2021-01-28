package com.zzr.jetpacktest.entity.hilt

import javax.inject.Inject

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/26
 */
class Truck @Inject constructor(val driver: Driver) {

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        println("Truck is delivering cargo. Driver is $driver")
        gasEngine.shutDown()
        electricEngine.shutDown()
    }
}