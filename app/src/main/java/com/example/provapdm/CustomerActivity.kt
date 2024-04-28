package com.example.provapdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustomerActivity : AppCompatActivity() {
    private lateinit var buttonRegisterCustomer: Button;
    private lateinit var buttonListCustomer: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        buttonRegisterCustomer = findViewById(R.id.buttonRegisterCustomer)
        buttonListCustomer = findViewById(R.id.buttonListCustomer)

        buttonRegisterCustomer.setOnClickListener {
            val intent = Intent(this, CustomerRegisterActivity::class.java)
            startActivity(intent)
        }

        buttonListCustomer.setOnClickListener {
            val intent = Intent(this, CustomerListViewActivity::class.java)
            startActivity(intent)
        }
    }
}