package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class CustomerEditActivity : AppCompatActivity() {

    lateinit var customerCPfField: TextView
    lateinit var customerNameField: EditText
    lateinit var customerPhoneField: EditText
    lateinit var customerEmailField: EditText
    lateinit var customerIsActiveSwitch: Switch
    lateinit var buttonUpdateCustomer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_edit)

        customerCPfField = findViewById(R.id.editCustomerCPFId)
        customerNameField = findViewById(R.id.editCustomerNameId)
        customerPhoneField = findViewById(R.id.editCustomerPhoneId)
        customerEmailField = findViewById(R.id.editCustomerEmailId)
        customerIsActiveSwitch = findViewById(R.id.switchEditCustomerId)
        buttonUpdateCustomer = findViewById(R.id.buttonUpdateCustomerId)

        intent.extras?.let {
            customerCPfField.text = it.getString("customerCPF")
            customerNameField.setText(it.getString("customerName"))
            customerPhoneField.setText(it.getString("customerPhone"))
            customerEmailField.setText(it.getString("customerEmail"))

            val customerIsActive = it.getInt("customerIsActive")
            customerIsActiveSwitch.isChecked = customerIsActive == 1

            customerIsActiveSwitch.text = if (customerIsActiveSwitch.isChecked) "Ativo" else "Inativo"
        }

        customerIsActiveSwitch.setOnCheckedChangeListener { _, isChecked ->
            customerIsActiveSwitch.text = if (isChecked) "Ativo" else "Inativo"
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

            val isActive: Int = if(customerIsActiveSwitch.isChecked) 1 else 0

            val customer = Customer(cpf, name, phone, email, isActive)
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