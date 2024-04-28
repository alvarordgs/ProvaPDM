package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TamaleOrderRegisterActivity : AppCompatActivity() {

    private lateinit var etFillingType: EditText
    private lateinit var etCheeseWeight: EditText
    private lateinit var etCustomerCPF: EditText
    private lateinit var buttonRegisterTamaleOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamale_order_register)

        etFillingType = findViewById(R.id.editTextFillingTypeCreateTamaleOrderId)
        etCheeseWeight = findViewById(R.id.editTextCheeseWeightCreateTamaleOrderId)
        etCustomerCPF = findViewById(R.id.editTextCustomerCPFCreateTamaleOrderId)
        buttonRegisterTamaleOrder = findViewById(R.id.buttonCreateTamaleOrderId)

        buttonRegisterTamaleOrder.setOnClickListener {
            createTamaleOrder()
        }
    }

    private fun createTamaleOrder() {
        try {
            val fillingType = etFillingType.text.toString()
            val cheeseWeight = etCheeseWeight.text.toString().toFloat()
            val customerCPF = etCustomerCPF.text.toString()

            val tamaleOrder = TamaleOrder(fillingType, cheeseWeight, customerCPF, null)

            val customerDAO = CustomerDAO(this)

            val customer = customerDAO.getActiveCustomerByCPF(tamaleOrder.customerCPf)

            if(customer != null) {
                val tamaleOrderDAO = TamaleOrderDAO(this)

                val wasCreated = tamaleOrderDAO.create(tamaleOrder)

                if(wasCreated) {
                    Toast.makeText(this, "Pedido cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    cleanFields()
                }
            } else {
                Toast.makeText(this, "O cliente informado não existe ou não está ativo!", Toast.LENGTH_SHORT).show()
            }

         } catch(e: Exception) {
             Toast.makeText(this, "Não foi possível cadastrar o pedido!", Toast.LENGTH_SHORT).show()
         }
    }

    private fun cleanFields() {
        etFillingType.text.clear()
        etCheeseWeight.text.clear()
        etCustomerCPF.text.clear()
    }
}