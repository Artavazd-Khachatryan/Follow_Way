package com.useruser.followay.ui.fragment.selectUserTypeFragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.LOGIN_CLICK_COMMAND
import com.useruser.followay.domain.models.OPEN_SEND_INVITATION_FRAGMENT_COMMAND

import com.useruser.followay.databinding.SelectUserTypeFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SelectUserTypeFragment : DaggerFragment() {

    companion object {

        val TAG = "SelectUserTypeFragment"

        fun newInstance() =
            SelectUserTypeFragment()
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SelectUserTypeViewModel
    private lateinit var activityViewModel: MainViewModel
    private lateinit var binding: SelectUserTypeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelAndBinding(inflater, container)
        observeCommands()

        return binding.root
    }


    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SelectUserTypeViewModel::class.java)
        activityViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        binding = SelectUserTypeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                LOGIN_CLICK_COMMAND -> {
                    activityViewModel.account?.userType = viewModel.userType
                    activityViewModel.command.value = Command(OPEN_SEND_INVITATION_FRAGMENT_COMMAND)
                }
            }
        })
    }

}
