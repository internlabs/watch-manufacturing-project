package com.example.aom_dials_app

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Statistics : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var result: TextView
    lateinit var adapter: StatisticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        //fetchData()
        StatisticsDropdown()
    }

    fun StatisticsDropdown() {
        val spinner: Spinner = findViewById(R.id.statisticsDropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.statistics_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        option = findViewById<Spinner>(R.id.statisticsDropdown)
        result = findViewById<TextView>(R.id.statisticsText)

        option.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                result.text = option.getItemAtPosition(position) as CharSequence?
                fetchData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                result.text = getString(R.string.result)
            }
        }
    }


    private fun fetchData() {
        val order = DataInterface.dataInstance.Getdata(1)
        order.enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                val order = response.body()
                if (order != null) {
                    Log.d("AOM DIALS", order.toString())
                    adapter = StatisticsAdapter(this@Statistics, order.articles)
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.adapter=adapter
                    recyclerView.layoutManager=LinearLayoutManager(this@Statistics)
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.d("AOM DIALS", "Error in fetching")
            }

        })
    }
}
