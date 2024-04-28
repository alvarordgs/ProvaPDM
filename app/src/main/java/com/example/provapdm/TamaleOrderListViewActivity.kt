package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TamaleOrderListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamale_order_list_view)

        val reciclerViewCustomers = findViewById<RecyclerView>(R.id.recyclerViewTamaleOrder)

        reciclerViewCustomers.layoutManager = LinearLayoutManager(this)
        reciclerViewCustomers.setHasFixedSize(true)

        val tamaleOrderDAO = TamaleOrderDAO(this)

        val tamaleOrderAdapter = TamaleOrderAdapter(this, tamaleOrderDAO.list());
        reciclerViewCustomers.adapter = tamaleOrderAdapter;
    }
}