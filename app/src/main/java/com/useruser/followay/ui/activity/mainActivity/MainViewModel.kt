package com.useruser.followay.ui.activity.mainActivity

import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.models.Account
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.utils.SingleLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    companion object

    val TAG = "MainViewModel"

    val command = SingleLiveData<Command>()

    var account: Account? = null

}