package com.useruser.followay.ui.fragment.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.useruser.followay.domain.models.Command
import com.useruser.followay.domain.models.OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND
import com.useruser.followay.databinding.LoginFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel
    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelAndBinding(inflater, container)

        observeCommands()

        return binding.root
    }


    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND -> {
                    mainViewModel.command.value = Command(OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND)
                    mainViewModel.account?.run {
                        name = viewModel.userName.get().toString()
                        password = viewModel.password.get().toString()
                        userType = viewModel.userType
                    }


                }
            }
        })
    }

}
