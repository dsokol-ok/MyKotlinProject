package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) :Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewModelScope.launch {

            val getResultProducts = viewModel.getProductsData()
            //заполнение экрана продуктами

            val hi =  Toast.makeText(applicationContext,"заполняем :)", Toast.LENGTH_SHORT)
            hi.show()

            Log.i("getResultProducts", getResultProducts.toString())

            // Creates a vertical Layout Manager
            products_list.layoutManager = LinearLayoutManager(this@MainActivity)

            // Access the RecyclerView Adapter and load the data into it
            products_list.adapter = ProductAdapter(getResultProducts, this@MainActivity)

        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }

    //private var selectedFragment: Fragment = ClientsFragment()

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener{

            menuItem ->

        when (menuItem.itemId) {
            R.id.nav_products -> {

                val hi =  Toast.makeText(applicationContext,"Остаемся в продуктах!", Toast.LENGTH_SHORT)
                hi.show()

                //selectedFragment = ProductsFragment()
                

                //startActivity(MainActivity.newInstance(this))
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
                val hi =  Toast.makeText(applicationContext,"Переходим в заказы!", Toast.LENGTH_SHORT)
                hi.show()
                //selectedFragment = OrdersFragment()
                startActivity(OrdersActivity.newInstance(this))
                //return@OnNavigationItemSelectedListener true
            }
        }

        //supportFragmentManager//
            // .beginTransaction()
            //.replace(R.id.fragment_container, selectedFragment)
            //.commit()
        return@OnNavigationItemSelectedListener true
    }


    fun addProduct(veiw: View) {

        val hi =  Toast.makeText(applicationContext,"Сейчас создадим продуктик! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(AddProductActivity.newInstance(this))

    }

    /*
    fun nextScreenOrders(view: View) {

        val hi =  Toast.makeText(applicationContext,"Переходим в заказы! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(OrdersActivity.newInstance(this))

    }

    fun nextScreenClients(view: View) {

        val hi =  Toast.makeText(applicationContext,"Переходим в клиенты! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(ClientsActivity.newInstance(this))

    }

     */



}
