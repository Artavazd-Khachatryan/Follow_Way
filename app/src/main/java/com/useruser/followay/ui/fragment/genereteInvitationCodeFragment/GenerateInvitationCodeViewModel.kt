package com.useruser.followay.ui.fragment.genereteInvitationCodeFragment

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class GenerateInvitationCodeViewModel @Inject constructor() : ViewModel() {

    val invitationCode = ObservableField<String>("")
    val isCodeGenerated = ObservableBoolean(false)


    fun generateCodeClick(view: View) {

    }

    fun doneClick(view: View) {

    }
}
