package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class AddProductsInOrder: AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        viewModel.liveData.observe(this) {

        }

         */

        viewModel.viewModelScope.launch {

            val getProductsOrder = viewModel.liveDataProducts
            //заполнение экрана продуктами

            val hi =  Toast.makeText(applicationContext,"заполняем :)", Toast.LENGTH_SHORT)
            hi.show()

             //Log.i("getResultProducts", getProductsOrder.toString())

            // Creates a vertical Layout Manager
            products_list.layoutManager = LinearLayoutManager(this@AddProductsInOrder)

            // Access the RecyclerView Adapter and load the data into it
            products_list.adapter = ProductsInOrderAdapter(getProductsOrder, this@AddProductsInOrder)

        }

    }


}