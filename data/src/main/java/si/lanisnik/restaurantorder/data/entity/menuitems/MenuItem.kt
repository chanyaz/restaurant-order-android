package si.lanisnik.restaurantorder.data.entity.menuitems

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = "menu_items")
data class MenuItem(
        @PrimaryKey
        val id: Int = 0,
        val title: String,
        val description: String,
        val image: String? = null,
        val price: Double,
        val category: FoodCategory
)