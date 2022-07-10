package com.useruser.followay.ui.activity.mainActivity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.useruser.followay.R
import com.useruser.followay.databinding.ActivityMainBinding
import com.useruser.followay.domain.addapters.MainViewPagerAdapter
import com.useruser.followay.domain.models.*
import com.useruser.followay.domain.utils.transparentStatusBar
import com.useruser.followay.ui.base.BaseActivity
import com.useruser.followay.ui.fragment.locationMapFragment.LocationMapFragment
import com.useruser.followay.ui.fragment.loginFragment.LoginFragment
import com.useruser.followay.ui.fragment.mainFragment.MainFragment
import com.useruser.followay.ui.fragment.phoneConnectedFragment.PhoneConnectedFragment
import com.useruser.followay.ui.fragment.selectNameAndGenderFragment.SelectNameAndGenderFragment
import com.useruser.followay.ui.fragment.selectUserTypeFragment.SelectUserTypeFragment
import com.useruser.followay.ui.fragment.sendInvitationFragment.SendInvitationFragment
import com.useruser.followay.ui.fragment.startInformationFragment.StartInformationFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    val TAG = "MainActivity"
    val FRAGMENT_CONTAINER_ID = R.id.fragment_container

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val fragmentList = mutableListOf(
        StartInformationFragment.newInstance(),
        MainFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()

        initBindingAndViewModel()
        initViewPager()

        observeCommands()
    }


    private fun initBindingAndViewModel() {
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initViewPager() {
        binding.viewPager.run {
            adapter = MainViewPagerAdapter(this@MainActivity, fragmentList)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position != 0) {
                        isUserInputEnabled = false
                    }
                }
            })
        }

        (binding.viewPager.getChildAt(0) as RecyclerView).overScrollMode = View.OVER_SCROLL_NEVER

    }

    private fun observeCommands() {
        viewModel.command.observe(this, Observer {
            when (it.command) {
                OPEN_LOGIN_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    LoginFragment.newInstance()
                )
                OPEN_SELECT_USER_TYPE_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    SelectUserTypeFragment.newInstance()
                )
                OPEN_SEND_INVITATION_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    SendInvitationFragment.newInstance()
                )
                OPEN_SELECT_NAME_GENDER_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    SelectNameAndGenderFragment.newInstance()
                )
                OPEN_PHONE_CONNECTED_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    PhoneConnectedFragment.newInstance()
                )
                OPEN_LOCATION_MAP_FRAGMENT_COMMAND -> replaceFragment(
                    FRAGMENT_CONTAINER_ID,
                    LocationMapFragment.newInstance()
                )
            }
        })
    }

}
