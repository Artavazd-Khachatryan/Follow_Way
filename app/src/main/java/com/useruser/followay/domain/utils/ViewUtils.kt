package com.useruser.followay.domain.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.createBitmap(): Bitmap {
    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay
        .getMetrics(displayMetrics)
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

    this.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
    this.layout(
        0, 0, displayMetrics.widthPixels,
        displayMetrics.heightPixels
    )
    //this.buildDrawingCache()
    val bitmap = Bitmap.createBitmap(
        this.getMeasuredWidth(),
        this.getMeasuredHeight(), Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    this.draw(canvas)
    return bitmap

}

fun createViewFromLayout(context: Context, layoutId: Int): View {
    return (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
        layoutId,
        null
    )
}
