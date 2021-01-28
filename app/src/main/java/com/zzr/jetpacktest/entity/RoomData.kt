package com.zzr.jetpacktest.entity

/**
 * @Author zzr
 * @Desc
 * @Date 2020/11/9
 */
data class RoomData(
    val bindType: Int,
    val deviceChannel: Int,
    val deviceId: Int,
    val deviceName: String,
    val deviceNum: Int,
    val deviceType: String,
    val deviceTypeDesc: String,
    val floorId: Int,
    val floorName: String,
    val imgType: String,
    val isSecurity: String,
    val isShow: Int,
    val linkageDeviceChannel: Int,
    val linkageDeviceNum: Int,
    val parentDeviceChannel: Int,
    val parentDeviceNum: Int,
    val zId: Int,
    val zoneId: Int,
    val zoneName: String
)