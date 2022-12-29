package com.example.aom_dials_app

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*

class createWorkOrder : AppCompatActivity() {

    private lateinit var id1: TextView
    private lateinit var id2:TextView
    private lateinit var selectImageButton: Button
    private lateinit var selectedImage: ImageView

    companion object{
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_work_order)

        supportActionBar?.hide()

        Material_Dropdown()
        BaseColorDropdown()
        FinishDropdown()
        selectImageButton = findViewById(R.id.selectButton)
        selectedImage=findViewById(R.id.selectedImagePreview)

        selectImageButton.setOnClickListener{
            imagePicker()
        }
    }

    fun Material_Dropdown() {
        val spinner: Spinner = findViewById(R.id.materialDropdown)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.material_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    fun BaseColorDropdown(){
        val spinner: Spinner = findViewById(R.id.baseColorDropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.basecolor_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun FinishDropdown(){
        val spinner: Spinner = findViewById(R.id.finishDropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.finish_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun datePickerDialog(view: View) {

        id1 = findViewById<TextView>(R.id.orderDate)
        id2 = findViewById<TextView>(R.id.displayDate)

        //Date picker using calender
        val calender = Calendar.getInstance()

        //displaying date
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calender.set(Calendar.YEAR,year)
            calender.set(Calendar.MONTH,month)
            calender.set(Calendar.DATE,dayOfMonth)
            updateLabel(calender)
        }

        id1.setOnClickListener{
            DatePickerDialog(this,datePicker,calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),calender.get(Calendar.DATE)).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateLabel(calender: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        id2.setText(sdf.format(calender.time))
    }

    fun onGenerateButtonClicked(view: View) {
        val text = "Work Order Generated Successfully!"
        val toast = Toast.makeText(this,text,Toast.LENGTH_LONG)
        toast.show()
        val intent = Intent(this@createWorkOrder,Home_Activity::class.java)
        startActivity(intent)
    }

    fun imagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== IMAGE_REQUEST_CODE && resultCode== RESULT_OK){
            selectedImage.setImageURI(data?.data)
        }
    }

}