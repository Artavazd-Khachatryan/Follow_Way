package com.useruser.followay.ui.fragment.selectUserTypeFragment

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.LOGIN_CLICK_COMMAND
import com.useruser.followay.domain.models.USER_TYPE_KID
import com.useruser.followay.domain.models.USER_TYPE_PARENT
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class SelectUserTypeViewModel @Inject constructor() : ViewModel() {

    val command = SingleLiveData<Command>()
    val isParentChecked = ObservableBoolean(true)

    val userType: Int
        get() = if (isParentChecked.get()) USER_TYPE_PARENT else USER_TYPE_KID

    fun loginClick(view: View) {
        command.value = Command(LOGIN_CLICK_COMMAND)
    }

    fun createGroupClick(view: View) {

    }


}
