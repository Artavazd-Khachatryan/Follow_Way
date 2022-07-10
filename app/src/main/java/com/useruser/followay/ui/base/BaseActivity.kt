package com.useruser.followay.ui.base

import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity


open class BaseActivity : DaggerAppCompatActivity() {


    fun addFragment(
        containerId: Int,
        fragment: Fragment,
        tag: String? = null,
        backStackName: String? = null
    ) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(backStackName)
            .add(containerId, fragment, tag)
            .commit()
    }


    fun removeFragment(fragment: Fragment, backStackName: String? = null) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(backStackName)
            .remove(fragment)
            .commit()
    }

    fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        tag: String? = null,
        backStackName: String? = null
    ) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(backStackName)
            .replace(containerId, fragment, tag)
            .commit()
    }
}