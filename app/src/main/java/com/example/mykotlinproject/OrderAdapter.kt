package com.example.mykotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderAdapter (val items : List<Order>, val context: Context) : RecyclerView.Adapter<OrderViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false))
    }

    // Binds each product in the ArrayList to a view
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val itemGet = items[position]

        holder.tvOrderType?.text =
            """Заказ: ${itemGet.Id}
От ${itemGet.CreatedDate} Сумма:  ${itemGet.Sum}""".trimIndent()
    }
}

class OrderViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvOrderType = view.tv_order_type
}
