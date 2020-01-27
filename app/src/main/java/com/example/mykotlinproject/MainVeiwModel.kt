package com.example.mykotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class MainVeiwModel : ViewModel() {

    companion object{
        val httpClient = OkHttpClient.Builder()
            .build()

         val backendService = Retrofit.Builder()
             .baseUrl("https://pastebin.com/raw/")
             //.baseUrl("https://10.10.0.118:5001/api/order")
             .client(httpClient)
             .addConverterFactory(GsonConverterFactory.create(Gson()))
             .build().run{create(ServiceCall::class.java)}

    }

    private val _livedata = MutableLiveData<List<String>>()

    val liveData: LiveData<List<String>>
    get() = _livedata

    //ORDER
    suspend fun createNewOrder(order: Order): Response<ResponseBody> {
        return backendService.createOrder(order)
    }

    suspend fun getOrderData(id: Int): Order {
       return backendService.getOrder(id)
    }

    suspend fun getOrdersData(): List<Order> {
        return backendService.getOrders()
    }

    //CLIENT
    suspend fun createNewClient(client: Client): Response<ResponseBody> {

        return backendService.createClient(client)
        /*
        try {

            val responce = backendService.createClient(client)
            return if(responce.isSuccessful){
                responce
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("errorCreate", e.message)
            return null
        }

         */
    }

    suspend fun getClientData(id: Int): Client {
        return backendService.getClient(id)
    }

    suspend fun getClientsData(): List<Client> {
        return backendService.getClients()
    }

    //PRODUCT
    suspend fun createNewProduct(product: Product): Response<ResponseBody> {
        return backendService.createProduct(product)
    }

    suspend fun getProductData(id: Int): Product {
        return backendService.getProduct(id)
    }

    suspend fun getProductsData(): List<Product> {
        return backendService.getProducts()
    }


}

interface ServiceCall {

    //ORDER
    @GET ( "api/order/{id}")
    suspend fun getOrder(
        @Path("id") id: Int
    ): Order

    //@GET ( "api/order")
    //suspend fun getOrders(): ArrayList<Order>

    //test
    @GET ( "6WRkSNWr")
    suspend fun getOrders():List<Order>


    @POST ( "api/order")
    suspend fun createOrder(order: Order): Response<ResponseBody>


    //CLIENT
    @GET ( "api/user/{id}")
    suspend fun getClient(
        @Path("id") id: Int
    ): Client

    //@GET ( "api/user/")
    //suspend fun getClients(): ArrayList<Client>

    //test
    @GET ( "4AUYEYS9")
    suspend fun getClients(): List<Client>

    @POST ( "4AUYEYS9")
    suspend fun createClient(client: Client): Response<ResponseBody>


    //PRODUCT
    @GET ( "api/product/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Product

    //@GET ( "api/product")
    //suspend fun getProducts(): ArrayList<Product>

    //for test
    @GET ( "NQzX9A32")
    suspend fun getProducts(): List<Product>

    @POST ( "NQzX9A32")
    suspend fun createProduct(product: Product): Response<ResponseBody>

}