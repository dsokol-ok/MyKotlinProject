package com.example.mykotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddProductActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) :Intent {
            return Intent(context, AddProductActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)


        addProduct_button.setOnClickListener {
            //val nameProduct = "nameProduct"
            //val cost = "cost"

            val Description = nameProduct_editText.text.toString()
            val Price = if (price_editText.text.toString() == "") {0} else {price_editText.text.toString().toInt()}


            Log.i("productNew", "Name = ${Description} Price = $Price")

            if(Description =="" || Price == 0){
                throw IllegalArgumentException("Заполните все данные.")
            } else {

                Log.i("applicationForProduct", "nameProduct = ${Description}, cost = $Price")

                val newProduct = Product(Description, Price.toDouble())

                Log.i("applicationForTest", "$newProduct")

                Log.i("попали", " В addProduct_button.setOnClickListener")
                viewModel.viewModelScope.launch {
                    Log.i("попали", " В viewModel.viewModelScope.launch")
                    val createResult = withContext(Dispatchers.IO){viewModel.createNewProduct(newProduct)}
                    //обработка ответа

                    //Log.i("createResult", " $createResult")
                    val done = Toast.makeText(applicationContext, "Сохранено", Toast.LENGTH_SHORT)
                    done.show()
                }
                startActivity(MainActivity.newInstance(this))
            }

        }

    }
/*
    fun saveProduct(view: View){

        val nameProduct = "nameProduct"
        val cost = "cost"

        val Name = nameProduct_editText.text.toString()
        val Price = price_editText.text.toString().toInt()

        //создать json на основании двух параметров
        Log.i( "applicationForProduct","$nameProduct = ${Name}, $cost = $Price")

        val newProduct = Product(Name, Price)

        Log.i( "applicationForTest","$newProduct")

        addProduct_button.setOnClickListener {

            Log.i("попали", " В addProduct_button.setOnClickListener")
            viewModel.viewModelScope.launch {
                Log.i("попали", " В viewModel.viewModelScope.launch")
                val createResult = viewModel.createNewProduct(newProduct)
                //обработка ответа

                Log.i("createResult", " $createResult")

                val done =  Toast.makeText(applicationContext,"Сохранено", Toast.LENGTH_SHORT)
                done.show()

            }
        }

        startActivity(MainActivity.newInstance(this))

    }

 */

}
