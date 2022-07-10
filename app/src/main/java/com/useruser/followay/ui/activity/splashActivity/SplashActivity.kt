package com.useruser.followay.ui.activity.splashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.useruser.followay.R
import com.useruser.followay.domain.utils.transparentStatusBar
import com.useruser.followay.ui.activity.mainActivity.MainActivity

class SplashActivity : AppCompatActivity() {

    private val HANDLE_TIME = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        transparentStatusBar()

        Handler().postDelayed({
            openMainActivity()
            finish()
        }, HANDLE_TIME)
    }


    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
