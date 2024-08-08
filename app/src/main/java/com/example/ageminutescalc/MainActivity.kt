package com.example.ageminutescalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var theSelectedDate : TextView? = null
    private var theAgeInMinutes : TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )
        setContentView(R.layout.activity_main)
        val selectBtn : Button = findViewById(R.id.btn_click_to_select)

        theSelectedDate = findViewById(R.id.the_date)
        theAgeInMinutes = findViewById(R.id.the_minutes)

        selectBtn.setOnClickListener{
            setButton1()
        }
    }

    private fun setButton1() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            {view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this, "Date selected", Toast.LENGTH_LONG).show()

                val dateSelecting = "$selectedDay/${selectedMonth+1}/$selectedYear"
                theSelectedDate?.text = dateSelecting

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(dateSelecting)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time/ 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/ 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        theAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            }, year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}




















