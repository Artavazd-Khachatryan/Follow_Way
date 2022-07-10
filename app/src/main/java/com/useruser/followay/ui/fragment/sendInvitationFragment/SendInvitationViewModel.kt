package com.useruser.followay.ui.fragment.sendInvitationFragment

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.SEND_INVITATION_CLICK_COMMAND
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class SendInvitationViewModel @Inject constructor() : ViewModel() {

    val command = SingleLiveData<Command>()
    val invitationCode = ObservableField<String>()

    fun sendInvitationClick(view: View) {
        command.value = Command(SEND_INVITATION_CLICK_COMMAND)
    }
}
