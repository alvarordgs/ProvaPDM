package com.example.provapdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TamaleOrderActivity : AppCompatActivity() {
    private lateinit var buttonRegisterTamaleOrder: Button;
    private lateinit var buttonListTamaleOrder: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamale_order)

        buttonRegisterTamaleOrder = findViewById(R.id.buttonRegisterTamaleOrder)
        buttonListTamaleOrder = findViewById(R.id.buttonListTamaleOrder)

        buttonRegisterTamaleOrder.setOnClickListener {
            val intent = Intent(this, TamaleOrderRegisterActivity::class.java)
            startActivity(intent)
        }

        buttonListTamaleOrder.setOnClickListener {
            val intent = Intent(this, TamaleOrderListViewActivity::class.java)
            startActivity(intent)
        }
    }
}