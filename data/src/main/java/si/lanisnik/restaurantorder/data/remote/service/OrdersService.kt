package si.lanisnik.restaurantorder.data.remote.service

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.data.remote.model.order.Order
import si.lanisnik.restaurantorder.data.remote.model.order.create.CreateOrderRequest
import si.lanisnik.restaurantorder.data.remote.NetConstants

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
interface OrdersService {

    @POST("order/create")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun createOrder(@Body request: CreateOrderRequest): Single<ResponseBody>

    @GET("order/history")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getOrderHistory(): Single<List<Order>>

}