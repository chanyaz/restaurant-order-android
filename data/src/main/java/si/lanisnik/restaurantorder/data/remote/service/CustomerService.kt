package si.lanisnik.restaurantorder.data.remote.service

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.data.remote.model.customer.CustomerDto
import si.lanisnik.restaurantorder.data.remote.model.customer.RegisterRequest
import si.lanisnik.restaurantorder.data.remote.model.SimpleIdResponse
import si.lanisnik.restaurantorder.data.remote.NetConstants

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerService {

    @POST("customer/login")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun login(): Single<CustomerDto>

    @POST("customer/register")
    fun register(@Body request: RegisterRequest): Single<SimpleIdResponse>

    @POST("customer/profile")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getProfile(): Single<CustomerDto>

}