package com.useruser.followay.ui.fragment.phoneConnectedFragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.GO_TO_MAP_COMMAND
import com.useruser.followay.domain.models.OPEN_LOCATION_MAP_FRAGMENT_COMMAND
import com.useruser.followay.databinding.PhoneConnectedFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PhoneConnectedFragment : DaggerFragment() {

    companion object {
        fun newInstance() =
            PhoneConnectedFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PhoneConnectedViewModel
    private lateinit var binding: PhoneConnectedFragmentBinding

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
            ViewModelProviders.of(this, viewModelFactory).get(PhoneConnectedViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)

        binding = PhoneConnectedFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                GO_TO_MAP_COMMAND -> {
                    mainViewModel.command.value = Command(OPEN_LOCATION_MAP_FRAGMENT_COMMAND)
                }
            }
        })
    }

}
