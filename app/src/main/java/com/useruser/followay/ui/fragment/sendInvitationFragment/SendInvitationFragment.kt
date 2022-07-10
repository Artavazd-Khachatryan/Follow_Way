package com.useruser.followay.ui.fragment.sendInvitationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.OPEN_SELECT_NAME_GENDER_FRAGMENT_COMMAND
import com.useruser.followay.domain.models.SEND_INVITATION_CLICK_COMMAND
import com.useruser.followay.databinding.SendInvitationFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SendInvitationFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            SendInvitationFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SendInvitationViewModel
    private lateinit var binding: SendInvitationFragmentBinding

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
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SendInvitationViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        binding = SendInvitationFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                SEND_INVITATION_CLICK_COMMAND -> {
                    mainViewModel.command.value = Command(OPEN_SELECT_NAME_GENDER_FRAGMENT_COMMAND)
                }
            }
        })
    }

}
