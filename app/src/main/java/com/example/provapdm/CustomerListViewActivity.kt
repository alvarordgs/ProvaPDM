package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomerListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list_view)

        val reciclerViewCustomers = findViewById<RecyclerView>(R.id.recyclerViewCustomer)

        reciclerViewCustomers.layoutManager = LinearLayoutManager(this)
        reciclerViewCustomers.setHasFixedSize(true)

        val customerDAO = CustomerDAO(this)

        val customerAdapter = CustomerAdapter(this, customerDAO.list());
        reciclerViewCustomers.adapter = customerAdapter;
    }
}