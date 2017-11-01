package si.lanisnik.restaurantorder.ui.onboarding.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.ui.onboarding.login.LoginActivity
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterActivity
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterActivity.Companion.EXTRA_STARTED_FROM_LOGIN
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class OnboardingNavigator @Inject constructor() {

    fun navigateToLogin(context: Context) {
        context.startActivity<LoginActivity>()
    }

    fun navigateToRegister(context: Context, fromLogin: Boolean) {
        context.startActivity<RegisterActivity>(EXTRA_STARTED_FROM_LOGIN to fromLogin)
    }

}