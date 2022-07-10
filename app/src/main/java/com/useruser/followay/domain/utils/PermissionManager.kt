package com.useruser.followay.domain.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

fun isPermissionGranted(grantResult: Int) = grantResult == PackageManager.PERMISSION_GRANTED

fun Context.permissionChecked(permission: String): Boolean {

    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
        return true
    }

    return false
}

fun Fragment.permissionChecked(permission: String): Boolean {
    context?.let {
        if (ActivityCompat.checkSelfPermission(
                this.context!!,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
    }

    return false
}

fun Activity.requestPermission(permissions: String, requestCode: Int) {
    this.requestPermissions(arrayOf(permissions), requestCode)
}

fun Fragment.requestPermission(permissions: String, requestCode: Int) {
    this.requestPermissions(arrayOf(permissions), requestCode)
}





