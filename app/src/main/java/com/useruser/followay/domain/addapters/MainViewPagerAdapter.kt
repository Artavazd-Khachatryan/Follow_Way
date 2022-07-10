package com.useruser.followay.domain.addapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.useruser.followay.ui.fragment.mainFragment.MainFragment
import com.useruser.followay.ui.fragment.startInformationFragment.StartInformationFragment
import androidx.viewpager.widget.PagerAdapter


class MainViewPagerAdapter(
    private val fragmentActivity: FragmentActivity,
    private val fragmentList: List<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment = fragmentList[position]


}