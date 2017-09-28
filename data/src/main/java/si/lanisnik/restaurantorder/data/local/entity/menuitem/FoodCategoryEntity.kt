package si.lanisnik.restaurantorder.data.local.entity.menuitem

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
@Entity(tableName = "food_category")
data class FoodCategoryEntity(
        @PrimaryKey
        val id: Int = 0,
        val title: String = ""
)