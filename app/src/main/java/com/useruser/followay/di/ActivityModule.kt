package com.useruser.followay.di

import com.useruser.followay.ui.fragment.genereteInvitationCodeFragment.GenerateInvitationCodeFragment
import com.useruser.followay.ui.fragment.phoneConnectedFragment.PhoneConnectedFragment
import com.useruser.followay.ui.fragment.locationMapFragment.LocationMapFragment
import com.useruser.followay.ui.fragment.loginFragment.LoginFragment
import com.useruser.followay.ui.fragment.mainFragment.MainFragment
import com.useruser.followay.ui.fragment.selectNameAndGenderFragment.SelectNameAndGenderFragment
import com.useruser.followay.ui.fragment.selectUserTypeFragment.SelectUserTypeFragment
import com.useruser.followay.ui.fragment.sendInvitationFragment.SendInvitationFragment
import com.useruser.followay.ui.fragment.startInformationFragment.StartInformationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeLocationMapFragment(): LocationMapFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeSelectUserTypeFragment(): SelectUserTypeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countableSendInvitationFragment(): SendInvitationFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countableSelectNameAndGenderFragment(): SelectNameAndGenderFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countablePhoneConnectedFragment(): PhoneConnectedFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countableLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countableGenerateInvitationCodeFragment(): GenerateInvitationCodeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun countableStartInformationFragment(): StartInformationFragment

}