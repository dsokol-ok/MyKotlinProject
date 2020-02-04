package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_addorder.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class AddOrderActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, AddOrderActivity::class.java)
        }
    }

    val adapterProduct = ProductsListOrderAdapter()
/*
    private val _livedata = MutableLiveData<List<Product>>()

    val liveData: LiveData<List<Product>>
        get() = _livedata

 */
    //OBSERVER
    private val productsListNewObserver = Observer<List<Product>> {
        if(it == null) return@Observer
            adapterProduct.items = it
    }

   // val productsListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addorder)

        products_list.adapter = adapterProduct

        viewModel.liveDataProducts.observe(this, productsListNewObserver)

        viewModel.viewModelScope.launch {

            //var selectedClient: Client

            val getResultClients = withContext(Dispatchers.IO){viewModel.getClientsData()}

            val adapter = ArrayAdapter(
                this@AddOrderActivity,
                R.layout.support_simple_spinner_dropdown_item,
                getResultClients.map (Client::Description)
            ).also {
                it.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            }

            clients.adapter = adapter

            clients.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //обработка пустого выбора
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selection.text = "Spinner selected: ${getResultClients[position].Description}"
                    val selectedClient = getResultClients[position]
                }
            }

        }

        addOrder_button.setOnClickListener {
            Log.i("попали", " В addOrder_button.setOnClickListener")
            saveOrder()
        }

        order_addProducts.setOnClickListener {
            showDialog()
        }
    }


    private fun showDialog(){

        //getProducts()
        viewModel.viewModelScope.launch {

            val items = withContext(Dispatchers.IO) { viewModel.getProductsData() }
            val listProducts = items.map (Product::Description).toTypedArray()
            val selectedList = ArrayList<Int>()

            val selectedListProduct = ArrayList<Product>()
            val builder = AlertDialog.Builder(this@AddOrderActivity)

            builder.setTitle("Выберете товары, которое нужно поместить в заказ")
                .setMultiChoiceItems(
                    listProducts , null)
                    {dialog, which, isChecked ->
                        if (isChecked) {
                            selectedList.add(which)
                        } else if (selectedList.contains(which)) {
                            selectedList.remove(Integer.valueOf(which))
                        }
                    }

                .setPositiveButton("DONE") { dialogInterface, i ->
                    val selectedStrings = ArrayList<String>()

                    for (j in selectedList.indices) {
                        selectedStrings.add(items[selectedList[j]].Description)
                        selectedListProduct.add(items[selectedList[j]])
                    }

                    viewModel.setliveData(selectedListProduct.toList())

                    Toast.makeText(
                        applicationContext,
                        "Вы выбрали: " + Arrays.toString(selectedStrings.toTypedArray()),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                .show()



        }
    }

    private fun saveOrder(){

        //val clientForOrder =

        //прием всех параметров

        //val test =  Toast.makeText(applicationContext,"$nameClient = ${fullName.text}, $login = ${loginText.text}", Toast.LENGTH_SHORT)
        //test.show()

        //val newOrder = Order()

            viewModel.viewModelScope.launch {
                Log.i("попали", " В viewModel.viewModelScope.launch")
                //val createResult = withContext(Dispatchers.IO){viewModel.createNewOrder(newOrder)}
                //обработка ответа

                //Log.i("createResult", " $createResult")
                val done =  Toast.makeText(applicationContext,"Сохранено", Toast.LENGTH_SHORT)
                done.show()
            }


        startActivity(OrdersActivity.newInstance(this))

    }
/*
    private fun getProducts() {
        viewModel.viewModelScope.launch {

            val items = withContext(Dispatchers.IO) { viewModel.getProductsData() }

        }
    }

 */

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //обработка пустого выбора
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //selection.text = "Spinner selected: ${getResultClients[position].Description}"

        //selectedClient = getResultClients[position]
    }

}
