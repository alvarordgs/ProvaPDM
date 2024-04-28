package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class CustomerEditActivity : AppCompatActivity() {

    lateinit var customerCPfField: TextView
    lateinit var customerNameField: EditText
    lateinit var customerPhoneField: EditText
    lateinit var customerEmailField: EditText
    lateinit var buttonUpdateCustomer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_edit)

        customerCPfField = findViewById(R.id.editCustomerCPFId)
        customerNameField = findViewById(R.id.editCustomerNameId)
        customerPhoneField = findViewById(R.id.editCustomerPhoneId)
        customerEmailField = findViewById(R.id.editCustomerEmailId)
        buttonUpdateCustomer = findViewById(R.id.buttonUpdateCustomerId)

        intent.extras?.let {
            customerCPfField.text = it.getString("customerCPF")
            customerNameField.setText(it.getString("customerName"))
            customerPhoneField.setText(it.getString("customerPhone"))
            customerEmailField.setText(it.getString("customerEmail"))
        }

        buttonUpdateCustomer.setOnClickListener {
            updateCustomer();
            finish()
        }
    }

    private fun updateCustomer() {
        try {
            val cpf = customerCPfField.text.toString()
            val name = customerNameField.text.toString()
            val phone = customerPhoneField.text.toString()
            val email = customerEmailField.text.toString()

            val customer = Customer(cpf, name, phone, email)
            val customerDAO = CustomerDAO(this)

            val updateCustomer = customerDAO.update(customer)

            if(updateCustomer) {
                Toast.makeText(this, "Cliente atualizado!", Toast.LENGTH_SHORT).show()
            }
        } catch(e: Exception) {
           Toast.makeText(this, "Não foi possìvel atualizar os dados do cliente!", Toast.LENGTH_SHORT).show()
        }
    }
}