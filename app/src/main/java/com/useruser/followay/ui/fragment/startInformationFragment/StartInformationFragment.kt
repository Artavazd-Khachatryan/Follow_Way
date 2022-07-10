package com.useruser.followay.ui.fragment.startInformationFragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView


import com.useruser.followay.databinding.StartInformationFragmentBinding
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import dagger.android.support.DaggerFragment

import javax.inject.Inject
import androidx.recyclerview.widget.ItemTouchHelper


class StartInformationFragment : DaggerFragment() {

    companion object {

        private val TAG = "${this.javaClass.name}"
        fun newInstance() = StartInformationFragment()
    }

    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory
    private lateinit var viewModel: StartInformationViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: StartInformationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelAndBinding(inflater, container)

        initRecyclerViewAndSetIndicator()

        return binding.root
    }

    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel =
            ViewModelProviders.of(this, viewModelfactory).get(StartInformationViewModel::class.java)
        mainViewModel =
            ViewModelProviders.of(activity!!, viewModelfactory).get(MainViewModel::class.java)
        binding = StartInformationFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initRecyclerViewAndSetIndicator() {
        val recyclerView = binding.rvInfoList
        val indicator = binding.infoListIndicator

        recyclerView.adapter = viewModel.infoListAdapter
        recyclerView.layoutManager = viewModel.layoutManager
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)


        indicator.attachToRecyclerView(recyclerView)
    }

}
