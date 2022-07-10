package com.useruser.followay.domain.utils

import android.content.Context
import android.os.BatteryManager

fun Context.getBatteryLevel(): Int {
    val batteryManager = this.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}