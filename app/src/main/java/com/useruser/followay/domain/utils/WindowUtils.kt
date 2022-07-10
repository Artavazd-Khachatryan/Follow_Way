package com.useruser.followay.domain.utils

import android.app.Activity
import android.view.WindowManager


fun Activity.transparentStatusBar() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

