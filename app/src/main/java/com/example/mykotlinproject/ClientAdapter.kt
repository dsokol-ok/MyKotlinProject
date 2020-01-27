package com.example.mykotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.client_list_item.view.*

class ClientAdapter (val items : List<Client>, val context: Context) : RecyclerView.Adapter<ClientViewHolder>() {

    // Gets the number of clients in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(LayoutInflater.from(context).inflate(R.layout.client_list_item, parent, false))
    }

    // Binds each client in the ArrayList to a view
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {

        val itemGet = items.get(position)

        holder?.tvClientType?.text = itemGet.Description + " Дата рождения: " + itemGet.Birthday
    }
}

class ClientViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvClientType = view.tv_client_type
}
