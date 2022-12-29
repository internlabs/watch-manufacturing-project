package com.example.aom_dials_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchAnOrder : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var result: TextView
    lateinit var adapter: RecyclerViewDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_an_order)

        searchOrderDropdown()
    }

    fun searchOrderDropdown(){
        val spinner: Spinner = findViewById(R.id.searchOrderDropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.searchOrder_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        option = findViewById<Spinner>(R.id.searchOrderDropdown)
        result = findViewById<TextView>(R.id.searchOrderText)

        option.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                result.text = option.getItemAtPosition(position) as CharSequence?
                getData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                result.text = getString(R.string.result)
            }
        }
    }

    private fun getData() {
        val order = DataInterface.dataInstance.Getdata(1)
        order.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                val order = response.body()
                if (order != null) {
                    Log.d("AOM DIALS", order.toString())
                    adapter = RecyclerViewDataAdapter(this@searchAnOrder, order.articles)
                    val recyclerView = findViewById<RecyclerView>(R.id.searchOrderRecyclerview)
                    recyclerView.adapter=adapter
                    recyclerView.layoutManager= LinearLayoutManager(this@searchAnOrder)
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.d("AOM DIALS", "Error in fetching")
            }

        })
    }
}