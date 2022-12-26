package com.example.aom_dials_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    fun createWorkOrder(view: View) {
        val intent = Intent(this@Home_Activity,createWorkOrder::class.java)
        startActivity(intent)
    }

    fun onStatisticsButtonClicked(view: View) {
        val intent = Intent(this@Home_Activity,Statistics::class.java)
        startActivity(intent)
    }
}