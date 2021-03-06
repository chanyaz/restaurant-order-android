package si.lanisnik.restaurantorder.data.repository.menuitem

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Interface defining methods for the data operations related to Menu items.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented.
 */
interface MenuItemDataStore {

    fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>

    fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable

    fun clearMenuItems(categoryId: Int): Completable

    fun isCached(categoryId: Int): Single<Boolean>

}