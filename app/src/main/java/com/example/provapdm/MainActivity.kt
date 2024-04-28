package com.example.provapdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonCustomer: Button
    private lateinit var buttonOrderTamale: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCustomer = findViewById(R.id.mainButtonCustomer)
        buttonOrderTamale = findViewById(R.id.mainButtonOrder)

        buttonCustomer.setOnClickListener {
            val intent = Intent(this, CustomerActivity::class.java)
            startActivity(intent)
        }

        buttonOrderTamale.setOnClickListener {
            val intent = Intent(this, TamaleOrderActivity::class.java)
            startActivity(intent)
        }

    }
}