package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class TamaleOrderEditActivity : AppCompatActivity() {

    private lateinit var textTamaleOrderId: TextView
    private lateinit var textTamaleOrderCustomerCPF: TextView
    private lateinit var etTamaleOrderFillingType: EditText
    private lateinit var etTamaleOrderCheeseWeight: EditText
    private lateinit var buttonUpdateTamaleOrder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamale_order_edit)

        textTamaleOrderId = findViewById(R.id.textIdTamaleOrderId)
        textTamaleOrderCustomerCPF = findViewById(R.id.textCustomerCPFEditTamaleOrder)
        etTamaleOrderFillingType = findViewById(R.id.editFillingTypeTamaleOrderId)
        etTamaleOrderCheeseWeight = findViewById(R.id.editCheeseWeightTamaleOrderId)
        buttonUpdateTamaleOrder = findViewById(R.id.buttonUpdateTamaleOrderId)

        intent.extras?.let {
            textTamaleOrderId.text = it.getString("tamaleOrderId")
            textTamaleOrderCustomerCPF.text = it.getString("tamaleOrderCustomerCPF")
            etTamaleOrderFillingType.setText(it.getString("tamaleOrderFillingType"))
            etTamaleOrderCheeseWeight.setText(it.getString("tamaleOrderCheeseWeight"))
        }

        buttonUpdateTamaleOrder.setOnClickListener {
            updateTamaleOrder()
        }
    }

    private fun updateTamaleOrder() {
        try {
            val fillingType = etTamaleOrderFillingType.text.toString()
            val cheeseWeight = etTamaleOrderCheeseWeight.text.toString().toFloat()
            val customerCPF = textTamaleOrderCustomerCPF.text.toString()
            val tamaleOrderId = textTamaleOrderId.text.toString().toInt()

            val tamaleOrder = TamaleOrder(fillingType, cheeseWeight, customerCPF, tamaleOrderId)
            val tamaleOrderDAO = TamaleOrderDAO(this)

            val wasUpdated = tamaleOrderDAO.update(tamaleOrder)

            if(wasUpdated) {
                Toast.makeText(this, "Pedido atualizado!", Toast.LENGTH_SHORT).show()
                finish()
            }

        } catch(e: Exception) {
            Toast.makeText(this, "Não foi possível atualizar o pedido!", Toast.LENGTH_SHORT).show()
        }
    }
}