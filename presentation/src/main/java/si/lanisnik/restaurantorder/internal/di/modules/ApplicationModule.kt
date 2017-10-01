package si.lanisnik.restaurantorder.internal.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.data.FoodCategoryDataRepository
import si.lanisnik.restaurantorder.data.MenuItemDataRepository
import si.lanisnik.restaurantorder.data.executor.JobThread
import si.lanisnik.restaurantorder.data.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.data.remote.service.CustomerService
import si.lanisnik.restaurantorder.data.remote.service.FoodCategoriesService
import si.lanisnik.restaurantorder.data.remote.service.MenuItemsService
import si.lanisnik.restaurantorder.data.remote.service.OrdersService
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import si.lanisnik.restaurantorder.internal.execution.MainThread
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
open class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideJobExecutionThread(jobThread: JobThread): JobExecutionThread = jobThread

    @Provides
    @Singleton
    fun providePostExecutionThread(mainThread: MainThread): PostExecutionThread = mainThread

    // region Repository

    @Provides
    @Singleton
    fun provideFoodCategoryRepository(foodCategoryRepository: FoodCategoryDataRepository): FoodCategoryRepository = foodCategoryRepository

    @Provides
    @Singleton
    fun provideMenuItemRepository(menuItemDataRepository: MenuItemDataRepository): MenuItemRepository = menuItemDataRepository

    // endregion

    // region Remote

    @Provides
    @Singleton
    fun provideFoodCategoryService(): FoodCategoriesService = RestaurantOrderServiceFactory.makeService(FoodCategoriesService::class.java)

    @Provides
    @Singleton
    fun provideCustomerService(): CustomerService = RestaurantOrderServiceFactory.makeService(CustomerService::class.java)

    @Provides
    @Singleton
    fun provideMenuItemsService(): MenuItemsService = RestaurantOrderServiceFactory.makeService(MenuItemsService::class.java)

    @Provides
    @Singleton
    fun provideOrdersService(): OrdersService = RestaurantOrderServiceFactory.makeService(OrdersService::class.java)

    // endregion
}