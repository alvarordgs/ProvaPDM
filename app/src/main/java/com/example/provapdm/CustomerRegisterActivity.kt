package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CustomerRegisterActivity : AppCompatActivity() {
    private lateinit var etCpf: EditText
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var buttonCreateCostumer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_customer)

        etCpf = findViewById(R.id.editTextCpf)
        etName = findViewById(R.id.editTextName)
        etPhone = findViewById(R.id.editTextPhone)
        etEmail = findViewById(R.id.editTextEmail)
        buttonCreateCostumer = findViewById(R.id.buttonCreateCostumer)

        buttonCreateCostumer.setOnClickListener {
            this.createCostumer()
        }
    }

    private fun createCostumer() {
        try {
            val cpf = this.etCpf.text.toString()
            val name = this.etName.text.toString()
            val phone = this.etPhone.text.toString()
            val email = this.etEmail.text.toString()

            val costumer = Customer(cpf, name, phone, email)
            val costumerDAO = CustomerDAO(this)

            val wasCreated = costumerDAO.create(costumer)

            if(wasCreated) {
                Toast.makeText(this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                this.cleanFields()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Não foi possível cadastrar o cliente!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cleanFields() {
        this.etCpf.text.clear()
        this.etName.text.clear()
        this.etPhone.text.clear()
        this.etEmail.text.clear()
    }
}