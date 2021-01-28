package com.zzr.jetpacktest.dsl

/**
 * @Author zzr
 * @Desc
 * @Date 2020/10/22
 */
fun main() {
//    val token = "39a2b33e0f654f0c93ef2a2841d50cff126544"
//    val jsonObject = JsonObject()
//    jsonObject.addProperty("token", token)
//    println("json==${jsonObject.toString()}")
//    val ss = Rsa.encrypt(jsonObject.toString(), HttpConstants.publicKey)
//    print("ss==$ss")

    val remark = "   "
    println("is remark null or empty: ${remark.length}")
//    test()
}

fun test(){
    val data = mutableListOf<String>("1","2","3","4")
    val data2 = data.filter { it =="1" }.toMutableList()
    println("data==${data},data2==${data2}")
}