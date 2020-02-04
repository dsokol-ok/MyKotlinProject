package com.example.mykotlinproject

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductsInOrderAdapter (private val items : LiveData<List<Product>>, val context: Context) : RecyclerView.Adapter<ProductsViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.value!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false))
    }

    // Binds each product in the ArrayList to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val itemGet = items.value?.get(position)

        holder.tvProductType?.text =
            """Артикул: ${itemGet?.Articul}
${itemGet?.Description}  Цена:  ${itemGet?.Price}""".trimIndent()

    }
}

class ProductsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvProductType = view.tv_product_type
}
