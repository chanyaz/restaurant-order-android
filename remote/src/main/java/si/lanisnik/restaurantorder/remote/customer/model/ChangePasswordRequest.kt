package si.lanisnik.restaurantorder.remote.customer.model

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
data class ChangePasswordRequest(val currentPassword: String, val newPassword: String)