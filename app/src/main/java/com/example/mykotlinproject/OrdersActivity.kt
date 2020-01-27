package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.coroutines.launch

class OrdersActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) :Intent {
            return Intent(context, OrdersActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        viewModel.liveData.observe(this) {

        }

        viewModel.viewModelScope.launch {

            val getResultOrders = viewModel.getOrdersData()

            // Creates a vertical Layout Manager
            orders_list.layoutManager = LinearLayoutManager(this@OrdersActivity)

            // Access the RecyclerView Adapter and load the data into it
            orders_list.adapter = OrderAdapter(getResultOrders, this@OrdersActivity)


        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener{

            menuItem ->

        when (menuItem.itemId) {
            R.id.nav_products -> {

                val hi =  Toast.makeText(applicationContext,"Переходим в продукты!", Toast.LENGTH_SHORT)
                hi.show()

                //selectedFragment = ProductsFragment()


                startActivity(MainActivity.newInstance(this))
                //return@OnNavigationItemSelectedListener true
            }
            R.id.nav_clients -> {
                val hi =  Toast.makeText(applicationContext,"Переходим в клиенты!", Toast.LENGTH_SHORT)
                hi.show()

                //selectedFragment = ClientsFragment()
                startActivity(ClientsActivity.newInstance(this))
                //return@OnNavigationItemSelectedListener true
            }
            R.id.nav_order -> {
                val hi =  Toast.makeText(applicationContext,"Остаемся в заказах!", Toast.LENGTH_SHORT)
                hi.show()
                //selectedFragment = OrdersFragment()
                //startActivity(OrdersActivity.newInstance(this))
                //return@OnNavigationItemSelectedListener true
            }
        }

        //supportFragmentManager//
        // .beginTransaction()
        //.replace(R.id.fragment_container, selectedFragment)
        //.commit()
        return@OnNavigationItemSelectedListener true
    }


    fun addOrder(view: View) {

        val hi =  Toast.makeText(applicationContext,"Сейчас создадим Заказ! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(AddOrderActivity.newInstance(this))

    }
/*
    fun nextScreenProducts(view: View) {

        val hi =  Toast.makeText(applicationContext,"Переходим в продукты! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(MainActivity.newInstance(this))

    }

    fun nextScreenClients(view: View) {

        val hi =  Toast.makeText(applicationContext,"Переходим в клиенты! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(ClientsActivity.newInstance(this))

    }

 */
}
