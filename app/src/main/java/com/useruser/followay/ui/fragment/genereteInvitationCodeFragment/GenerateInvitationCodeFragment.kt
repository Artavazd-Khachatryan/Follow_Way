package com.useruser.followay.ui.fragment.genereteInvitationCodeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.useruser.followay.databinding.GenerateInvitationCodeFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import javax.inject.Inject

class GenerateInvitationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = GenerateInvitationCodeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GenerateInvitationCodeViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: GenerateInvitationCodeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelAndFragment(inflater, container)
        return binding.root
    }


    private fun initViewModelAndFragment(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GenerateInvitationCodeViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        binding = GenerateInvitationCodeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}
