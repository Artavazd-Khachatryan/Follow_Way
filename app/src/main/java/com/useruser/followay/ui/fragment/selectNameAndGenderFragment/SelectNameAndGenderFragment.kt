package com.useruser.followay.ui.fragment.selectNameAndGenderFragment

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.useruser.followay.domain.models.*
import com.useruser.followay.databinding.SelectNameGenderFragmentBinding
import com.useruser.followay.domain.utils.isPermissionGranted
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import com.useruser.followay.domain.utils.openGallery
import com.useruser.followay.domain.utils.permissionChecked
import com.useruser.followay.domain.utils.requestPermission

import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SelectNameAndGenderFragment : DaggerFragment() {

    companion object {
        private val TAG = "SelectNameAndGenderFragment"

        fun newInstance() =
            SelectNameAndGenderFragment()
    }

    @Inject
    lateinit var viewModelInflater: ViewModelProvider.Factory
    private lateinit var viewModel: SelectNameAndGenderViewModel
    private lateinit var binding: SelectNameGenderFragmentBinding

    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelAndBinding(inflater, container)

        observeCommands()

        return binding.root
    }

    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel = ViewModelProviders.of(this, viewModelInflater)
            .get(SelectNameAndGenderViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelInflater).get(MainViewModel::class.java)
        binding = SelectNameGenderFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                DONE_CLICK_COMMAND -> {
                    mainViewModel.command.value = Command(OPEN_PHONE_CONNECTED_FRAGMENT_COMMAND)
                    mainViewModel.account?.name = viewModel.userName.get()!!
                }

                OPEN_GALLERY_COMMAND -> {
                    if (!permissionChecked(READ_EXTERNAL_STORAGE)) {
                        requestPermission(READ_EXTERNAL_STORAGE, REQUEST_CODE_READ_EXTERNAL_STORAGE)
                    } else {
                        openGallery(REQUEST_CODE_OPEN_GALLERY)
                    }
                }

            }
        })
    }

    @SuppressLint("LongLogTag")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_OPEN_GALLERY -> {
                    data?.let { viewModel.userImagePath.value = data.data.toString() }
                }

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_READ_EXTERNAL_STORAGE -> if (isPermissionGranted(grantResults[0])) openGallery(
                REQUEST_CODE_OPEN_GALLERY
            )
        }
    }

}
