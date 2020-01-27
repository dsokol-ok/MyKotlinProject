package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_clients.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientsActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) :Intent {
            return Intent(context, ClientsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)

        viewModel.viewModelScope.launch {

            val getResultClients = withContext(Dispatchers.IO){viewModel.getClientsData()}

            // Creates a vertical Layout Manager
            clients_list.layoutManager = LinearLayoutManager(this@ClientsActivity)

            // Access the RecyclerView Adapter and load the data into it
            clients_list.adapter = ClientAdapter(getResultClients, this@ClientsActivity)


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
                val hi =  Toast.makeText(applicationContext,"Остаемся в клиентах!", Toast.LENGTH_SHORT)
                hi.show()

                //selectedFragment = ClientsFragment()
                //startActivity(ClientsActivity.newInstance(this))
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

    fun addClient(view: View) {

        val hi =  Toast.makeText(applicationContext,"Сейчас создадим клиента! :)", Toast.LENGTH_SHORT)
        hi.show()

        startActivity(AddClientActivity.newInstance(this))

    }

}
