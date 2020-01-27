package com.example.mykotlinproject

//import androidx.room.Entity
//import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//const val CLIENT_TABLE_NAME = "client"
//const val PRODUCT_TABLE_NAME = "product"
//const val ORDER_TABLE_NAME = "order"
//const val ORDER_SKU_TABLE_NAME = "order_sku"

//@Entity(tableName = CLIENT_TABLE_NAME)
data class Client(
    @SerializedName("Name") var Description: String,
    @SerializedName("Login") var Login: String,
    @SerializedName("Password") var Password: String,
    @SerializedName("Birthday") val Birthday: String,

    @SerializedName("IsDeleted") val IsDeleted: Boolean = false,
    @SerializedName("Id") val Id: Int = 0
)

//@Entity(tableName = ORDER_TABLE_NAME)
data class Order(
    @SerializedName("UserId") var ClientId: Int,
    @SerializedName("Sum") val Sum: Double,
    @SerializedName("Products") val Products: List<Product>,
    @SerializedName("CreatedDate") val CreatedDate: String,

    @SerializedName("Number") val Id: Int = 0
)

//@Entity(tableName = PRODUCT_TABLE_NAME)
data class Product(
    @SerializedName("Name") var Description: String,
    @SerializedName("Price") var Price: Int,
    @SerializedName("SKU") val Articul: String = "",

    @SerializedName("IsDeleted") var IsDeleted: Boolean = false,
    @SerializedName("Id") val Id: Int = 0
)

/*
//@Entity(tableName = ORDER_SKU_TABLE_NAME)
data class OrderSKU(
    val ref: UUID,
    val sku: UUID,
    //val cost: Int,
    //var isFinished: Boolean = false,
    //val dateOfCreating: OffsetDateTime = OffsetDateTime.now()

    //@PrimaryKey
    val Id: UUID = UUID.randomUUID()

)
 */