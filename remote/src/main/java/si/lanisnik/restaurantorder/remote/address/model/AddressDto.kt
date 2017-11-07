package si.lanisnik.restaurantorder.remote.address.model

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
data class AddressDto(
        val id: Int = 0,
        val street: String = "",
        val number: String = "",
        val city: String = "",
        val postcode: Int = 0,
        val note: String? = null,
        val selected: Boolean = false,
        val deleted: Boolean = false,
        val customerId: Int = 0
)