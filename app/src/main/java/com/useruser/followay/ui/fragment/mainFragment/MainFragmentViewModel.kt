package com.useruser.followay.ui.fragment.mainFragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.*
import com.useruser.followay.domain.utils.SingleLiveData
import com.useruser.followay.domain.utils.handleGoogleSignInResult
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor() : ViewModel() {

    companion object

    val TAG = "MainFragmentViewModel"

    val command = SingleLiveData<Command>()

    fun loginClick(view: View) {
        command.value = Command(OPEN_LOGIN_FRAGMENT_COMMAND)
    }

    fun googleRegistrationClick(view: View) {
        command.value = Command(GOOGLE_LOGIN_COMMAND)
    }

    fun facebookRegistrationClick(view: View) {
        command.value = Command(FACEBOOK_LOGIN_COMMAND)
    }

    fun handleSignInResult(registrationType: Int, data: Intent?): Account? {

        val account = when (registrationType) {
            GOOGLE_REGISTRATION -> handleGoogleSignInResult(data)
            else -> null
        }

        return account
    }

}
