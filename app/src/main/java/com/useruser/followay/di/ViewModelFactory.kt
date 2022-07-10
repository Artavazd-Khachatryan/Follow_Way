package com.useruser.followay.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.useruser.followay.ui.activity.mainActivity.MainViewModel
import com.useruser.followay.ui.fragment.genereteInvitationCodeFragment.GenerateInvitationCodeViewModel
import com.useruser.followay.ui.fragment.phoneConnectedFragment.PhoneConnectedViewModel
import com.useruser.followay.ui.fragment.locationMapFragment.LocationMapViewModel
import com.useruser.followay.ui.fragment.loginFragment.LoginViewModel
import com.useruser.followay.ui.fragment.mainFragment.MainFragmentViewModel
import com.useruser.followay.ui.fragment.selectNameAndGenderFragment.SelectNameAndGenderViewModel
import com.useruser.followay.ui.fragment.selectUserTypeFragment.SelectUserTypeViewModel
import com.useruser.followay.ui.fragment.sendInvitationFragment.SendInvitationViewModel
import com.useruser.followay.ui.fragment.startInformationFragment.StartInformationViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("model class $modelClass not found")
        return viewModelProvider.get() as T
    }
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKay(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKay(MainViewModel::class)
    internal abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(MainFragmentViewModel::class)
    internal abstract fun mainFragmentViewModel(fragmentViewModel: MainFragmentViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKay(LocationMapViewModel::class)
    internal abstract fun locationMapViewModel(viewModel: LocationMapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(SelectUserTypeViewModel::class)
    internal abstract fun selectUserTypeViewModel(viewModel: SelectUserTypeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(SendInvitationViewModel::class)
    internal abstract fun sendInvitationViewModel(viewModel: SendInvitationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(SelectNameAndGenderViewModel::class)
    internal abstract fun selectNameAndGenderViewModel(viewModel: SelectNameAndGenderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(PhoneConnectedViewModel::class)
    internal abstract fun phoneConnectedViewModel(viewModel: PhoneConnectedViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKay(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKay(GenerateInvitationCodeViewModel::class)
    internal abstract fun generateInvitationCodeViewModel(viewModel: GenerateInvitationCodeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKay(StartInformationViewModel::class)
    internal abstract fun startInformationViewModel(viewModel: StartInformationViewModel): ViewModel

}
