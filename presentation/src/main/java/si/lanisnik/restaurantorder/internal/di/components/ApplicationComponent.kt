package si.lanisnik.restaurantorder.internal.di.components

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import si.lanisnik.restaurantorder.RestaurantOrderApp
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import si.lanisnik.restaurantorder.internal.di.modules.ActivityBindingModule
import si.lanisnik.restaurantorder.internal.di.modules.ApplicationModule
import javax.inject.Singleton


/**
 * A component whose lifetime is the life of the application.
 */
@Singleton  // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = arrayOf(ApplicationModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {
    fun inject(application: RestaurantOrderApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    // region Exposed to sub-graphs

    fun context(): Context
    fun jobExecutionThread(): JobExecutionThread
    fun postExecutionThread(): PostExecutionThread

    // region Repositories

    fun menuItemRepository(): MenuItemRepository

    // endregion Repositories

    // endregion
}