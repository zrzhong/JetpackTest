package com.zzr.jetpacktest.kotlin_test

import com.google.gson.Gson
import com.zzr.jetpacktest.entity.AddDevResponse
import com.zzr.jetpacktest.entity.Student

fun main() {
//    val trans = object : Transformer<Person> {
//        override fun transform(t: Person): String {
//            return "${t.name} ${t.age}"
//        }
//    }
//    handleTransformer(trans)

    val a = "ok"
    val b = "ok"
    val uuid = "bb44d16be71743258a6e6164d2ab5499"
    val result =
        "{\"data\":{\"result\":\"ok\",\"devModleSw\":\"V2.1.8\",\"plugMult\":0,\"devNo\":348,\"plugDiv\":0,\"devModleId\":\"HA-LGT-C30H\",\"devChNum\":1,\"uuid\":\"bb44d16be71743258a6e6164d2ab5499\",\"devModleHw\":\"0\",\"chList\":[{\"devType\":256,\"devCh\":0}],\"devIeeeAddr\":\"124b001aaecbf5\"},\"fromDev\":\"g_83290\",\"toDev\":\"a_18950\"}"
    val devResponse: AddDevResponse? = Gson().fromJson(result, AddDevResponse::class.java)
    devResponse?.data?.let {
        if (uuid == it.uuid) {
            if (it.isSucceed) {
                println("result==${it.result}")
            }
        }
    }
    println(a == b)
}

fun handleTransformer(trans: Transformer<Student>) {
    val student = Student("Tom", 19)
    val result = trans.transform(student)
}