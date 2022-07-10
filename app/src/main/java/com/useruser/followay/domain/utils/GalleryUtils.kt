package com.useruser.followay.domain.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

fun Activity.openGallery(requestCode: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode)
}

fun Fragment.openGallery(requestCode: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode)
}