package com.useruser.followay.domain.utils

import android.text.format.DateFormat
import java.util.*


//MMM.dd
//HH:mm
fun getCurrentTime(format: String): CharSequence {
    return DateFormat.format(format, Calendar.getInstance().timeInMillis)
}