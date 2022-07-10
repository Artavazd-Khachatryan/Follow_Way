package com.useruser.followay.ui.fragment.mainFragment

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.useruser.followay.domain.models.*
import com.useruser.followay.databinding.MainFragmentBinding
import com.useruser.followay.domain.utils.permissionChecked
import com.useruser.followay.domain.utils.requestPermission
import com.useruser.followay.domain.utils.signInGoogle
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    companion object {

        private val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainFragmentViewModel
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initViewModelAndBinding(inflater, container)

        checkLocationPermission(context)

        observeCommands()

        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == DaggerAppCompatActivity.RESULT_OK) {

            when (requestCode) {
                REQUEST_CODE_GOOGLE_SIGN_IN -> {
                    val account = viewModel.handleSignInResult(GOOGLE_REGISTRATION, data)
                    account?.let {
                        mainViewModel.account = it
                        mainViewModel.command.value =
                            Command(OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND)
                    }
                }
            }

        }
    }

    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainFragmentViewModel::class.java)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                OPEN_LOGIN_FRAGMENT_COMMAND -> {
                    mainViewModel.command.value = Command(OPEN_LOGIN_FRAGMENT_COMMAND)
                }
                GOOGLE_LOGIN_COMMAND -> signInGoogle()
                FACEBOOK_LOGIN_COMMAND -> {}
            }
        })
    }

    private fun checkLocationPermission(context: Context?) {
        context?.let {
            if (!permissionChecked(Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    REQUEST_CODE_ACCESS_FINE_LOCATION
                )
            }

            if (!permissionChecked(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                requestPermission(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    REQUEST_CODE_ACCESS_BACKGROUND_LOCATION
                )
            }
        }

    }
}
