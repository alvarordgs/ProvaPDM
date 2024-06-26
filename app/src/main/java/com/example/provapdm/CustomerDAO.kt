package com.example.provapdm

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.lang.StringBuilder

class CustomerDAO(context: Context) {
    private val myDataBase =  MiiBaoDB(context);

    fun create(customer: Customer): Boolean {
        val db = this.myDataBase.writableDatabase;

        val cv = ContentValues().apply {
            put("cpf", customer.cpf)
            put("nome", customer.name)
            put("telefone", customer.phone)
            put("email", customer.email)
            put("ativo", customer.isActive)
        }

        val result = db.insert("cliente", null, cv);
        Log.i("Teste", "Inserção $result");

        if(result == -1L) {
            throw Exception("Não foi possível inserir cliente.");
        }

        return true;
    }

    fun list():MutableList<Customer> {
        val customerList = mutableListOf<Customer>();
        val db = this.myDataBase.readableDatabase;
        val sql = "SELECT * FROM cliente";
        val cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            do {
                val cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                val name = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                val phone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"));
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                val isActive = cursor.getString(cursor.getColumnIndexOrThrow("ativo"));
                val customer = Customer(cpf, name, phone, email, isActive.toInt());
                customerList.add(customer);
            } while(cursor.moveToNext());
        }

        cursor.close();
        return customerList;
    }

    fun getActiveCustomerByCPF(cpf: String): Customer? {
        val db = this.myDataBase.readableDatabase;
        val sql = "SELECT * FROM cliente WHERE cpf = ? AND ativo = 1";
        val cursor = db.rawQuery(sql, arrayOf(cpf));
        var customer: Customer? = null

        if(cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val customerCPF = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            val isActive = cursor.getString(cursor.getColumnIndexOrThrow("ativo"))

            customer = Customer(customerCPF, name, phone, email, isActive.toInt())
        }

        cursor.close()

        db.close()

        return customer
    }

    fun update(customer: Customer): Boolean {
        val db = this.myDataBase.writableDatabase;
        val cv = ContentValues().apply {
            put("nome", customer.name);
            put("telefone", customer.phone);
            put("email", customer.email);
            put("ativo", customer.isActive)
        }

        Log.i("Customer Data", " -> " + customer)

        val affectedRows = db.update("cliente", cv, "cpf = ?", arrayOf(customer.cpf));

        Log.i("Linhas afetadas", " -> " + affectedRows)

        db.close();

        return affectedRows > 0;
    }
}