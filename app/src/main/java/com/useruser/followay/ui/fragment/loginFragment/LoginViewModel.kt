package com.useruser.followay.ui.fragment.loginFragment

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND
import com.useruser.followay.domain.models.USER_GANDER_FEMAIL
import com.useruser.followay.domain.models.USER_GENDER_MALE
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    val command = SingleLiveData<Command>()

    val userName = ObservableField<String>()
    val password = ObservableField<String>()

    val userNameError = ObservableField<String>()
    val passwordError = ObservableField<String>()

    val isMale = ObservableBoolean(true)

    val userType: Int
        get() = if (isMale.get()) USER_GENDER_MALE else USER_GANDER_FEMAIL

    fun saveClick(view: View) {
        if (review()) {
            command.value = Command(OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND)
        }

    }

    private fun review(): Boolean {
        var review = true
        if (userName.get().isNullOrBlank()) {
            userNameError.set("User name is empty")
            review = false
        } else {
            userNameError.set("")
        }

        if (password.get().isNullOrBlank()) {
            passwordError.set("Password is empty")
            review = false
        } else {
            passwordError.set("")
        }

        return review
    }

}
