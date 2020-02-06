package com.example.mykotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_list_item.view.*


class ProductsListOrderAdapter(val context: Context) :
    RecyclerView.Adapter<ProductsListOrderViewHolder>() {

    private val _items : MutableList<Product> = mutableListOf()

    var items: List<Product>
    get()=_items
        set(value) {
            _items.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListOrderViewHolder {
        return ProductsListOrderViewHolder(LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return _items.count()
    }

    override fun onBindViewHolder(holder: ProductsListOrderViewHolder, position: Int) {
        val itemGet = _items.get(position)

        holder.tvProductType.text =
            """Артикул: ${itemGet.Articul}
${itemGet.Description}  Цена:  ${itemGet.Price}""".trimIndent()

    }
}

class ProductsListOrderViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvProductType = view.tv_product_type
}