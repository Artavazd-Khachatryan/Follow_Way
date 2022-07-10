package com.useruser.followay.ui.fragment.selectNameAndGenderFragment

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.DONE_CLICK_COMMAND
import com.useruser.followay.domain.models.OPEN_GALLERY_COMMAND
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class SelectNameAndGenderViewModel @Inject constructor() : ViewModel() {

    val command = SingleLiveData<Command>()
    val userImagePath = MutableLiveData<String>("")

    val userName = ObservableField<String>()
    val userNameError = ObservableField<String>()

    val isMaleSelected = ObservableBoolean(true)

    fun chooseImageClick(view: View) {
        command.value = Command(OPEN_GALLERY_COMMAND)
    }

    fun doneClick(view: View) {
        if (review()) {
            command.value = Command(DONE_CLICK_COMMAND)
            userNameError.set("")

        } else {
            userNameError.set("User name is empty")
        }
    }

    private fun review(): Boolean {
        if (userName.get().isNullOrBlank()) {
            return false
        }

        return true
    }


}
