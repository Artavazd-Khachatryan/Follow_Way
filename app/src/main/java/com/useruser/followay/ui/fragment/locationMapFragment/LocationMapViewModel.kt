package com.useruser.followay.ui.fragment.locationMapFragment

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.useruser.followay.domain.addapters.InvitedUserModel
import com.useruser.followay.domain.addapters.InvitedUsersAdapter
import com.useruser.followay.domain.addapters.UserTrajectoryListAdapter
import com.useruser.followay.domain.addapters.UserTrajectoryModel
import com.useruser.followay.domain.usecases.LocationUseCase
import javax.inject.Inject

class LocationMapViewModel @Inject constructor(private val locationUseCase: LocationUseCase) :
    ViewModel() {

    private val isMapFullVisible = MutableLiveData<Boolean>()
    private val isListFullVisible = MutableLiveData<Boolean>()

    val userTrajectoryListAdapter = UserTrajectoryListAdapter().apply {
        submitList(
            listOf(
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00"),
                UserTrajectoryModel("Saryan 1", "12:00")
            )
        )
    }

    val invitedUsersAdapter = InvitedUsersAdapter().apply {
        submitList(
            mutableListOf(
                InvitedUserModel(Color.parseColor("#3C3C62")),
                InvitedUserModel(Color.parseColor("#802F9C")),
                InvitedUserModel(Color.parseColor("#0B9999")),
                InvitedUserModel(Color.parseColor("#9C2F56")),
                InvitedUserModel(Color.parseColor("#C71C1C")),
                InvitedUserModel(Color.parseColor("#3C3C62")),
                InvitedUserModel(Color.parseColor("#802F9C")),
                InvitedUserModel(Color.parseColor("#0B9999")),
                InvitedUserModel(Color.parseColor("#9C2F56")),
                InvitedUserModel(Color.parseColor("#C71C1C"))
            )
        )

        listAddClick = {
            Log.d(
                "TAG",
                "--------------------------list add click------------------------------------"
            )
        }
    }

    fun getCurrentLocation() = locationUseCase.getCurrentLocation()

    fun getLocationForUpdates() = locationUseCase.requestCurrentLocationUpdates()

    fun setMapFull() {
        isListFullVisible.value = false
        isMapFullVisible.value = true
    }

}
