package com.example.mykotlinproject

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_addclient.*
import kotlinx.coroutines.launch
import java.util.*


class AddClientActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVeiwModel>()

    companion object {
        fun newInstance(context: Context) : Intent {
            return Intent(context, AddClientActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addclient)

        addClient_button.setOnClickListener {
            Log.i("попали", " В addClient_button.setOnClickListener")
            saveClient()
        }

    }


    private var dateAndTime: Calendar = Calendar.getInstance()
    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View?) {
        DatePickerDialog(
            this@AddClientActivity, d,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    // установка обработчика выбора даты
    private var d =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            //val date = OffsetDateTime.

            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth


            setInitialDateTime()
        }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
        dateOfBirth.text = DateUtils.formatDateTime(
            this,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
            or DateUtils.FORMAT_SHOW_TIME
        )
    }

    private fun saveClient(){

        //OffsetDateTime.parse("").toLocalDateTime()

        val Description = fullName_editText.text.toString()
        val Login = login_editText.text.toString()
        val Password = password_editText.text.toString()

        val test =  Toast.makeText(applicationContext,"nameClient = ${Description}, login = $Login", Toast.LENGTH_SHORT)
        test.show()

        Log.i("applicationForClient", "nameClient = ${Description}, login = $Login")
        val test2 =  Toast.makeText(applicationContext,"password = ${Password}, dateOfBirth = " + dateOfBirth.text, Toast.LENGTH_SHORT)
        test2.show()
        Log.i("applicationForClient", "password = ${Password}, dateOfBirth = " + dateOfBirth.text)
        val newClient = Client(Description, Login, Password, dateOfBirth.text.toString())

        Log.i("dateOfBirth.text",
            dateOfBirth.text.toString()
        )

            viewModel.viewModelScope.launch {
                Log.i("попали", " В viewModel.viewModelScope.launch")
                /*
                try {
                    val createResult = withContext(Dispatchers.IO){viewModel.createNewClient(newClient)}
                    Log.i("createResult", " ${createResult.body()}  ${createResult.errorBody()}")
                } catch (e: Exception) {
                    Log.e("errorCreate", e.message)
                }

                 */

                //обработка ответа

                //Log.i("createResult", " $createResult")

                val done =  Toast.makeText(applicationContext,"Сохранено", Toast.LENGTH_SHORT)
                done.show()
            }

            startActivity(ClientsActivity.newInstance(this))

    }

}
