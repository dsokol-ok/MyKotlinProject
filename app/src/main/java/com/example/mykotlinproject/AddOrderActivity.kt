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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_addorder.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddOrderActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, AddOrderActivity::class.java)
        }
    }

    private val _livedata = MutableLiveData<List<Product>>()

    val liveData: LiveData<List<Product>>
        get() = _livedata

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addorder)

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

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //обработка пустого выбора
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //selection.text = "Spinner selected: ${getResultClients[position].Description}"

        //selectedClient = getResultClients[position]
    }

}
