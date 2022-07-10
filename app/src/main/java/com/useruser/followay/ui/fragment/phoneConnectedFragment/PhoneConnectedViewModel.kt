package com.useruser.followay.ui.fragment.phoneConnectedFragment

import android.view.View
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.GO_TO_MAP_COMMAND
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class PhoneConnectedViewModel @Inject constructor() : ViewModel() {

    val command = SingleLiveData<Command>()

    fun goToMapClick(view: View) {
        command.value = Command(GO_TO_MAP_COMMAND)
    }
}
