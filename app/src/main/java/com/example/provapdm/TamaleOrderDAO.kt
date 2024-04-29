package com.example.provapdm

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.lang.StringBuilder

class TamaleOrderDAO(context: Context) {

    private val myDataBase = MiiBaoDB(context)
    fun create(tamaleOrder: TamaleOrder): Boolean {

        val db = myDataBase.writableDatabase

        val cv = ContentValues().apply {
            put("tipo_de_recheio", tamaleOrder.fillingType)
            put("peso_do_queijo", tamaleOrder.cheeseWeight)
            put("id_cliente", tamaleOrder.customerCPf)
        }

        val result = db.insert("pedido_pamonha", null, cv)
        Log.i("Teste", "Inserção $result");

        if(result == -1L) {
            throw Exception("Não foi possível inserir o pedido.");
        }

        return true
    }

    fun list(): MutableList<TamaleOrder> {
        val tamaleOrderList = mutableListOf<TamaleOrder>()
        val db = myDataBase.readableDatabase
        val sql = "SELECT * FROM pedido_pamonha"
        val cursor = db.rawQuery(sql, null)

        if(cursor.moveToFirst()) {
            do {
                val tamaleOrderId = cursor.getString(cursor.getColumnIndexOrThrow("id_pedido_pamonha"))
                val fillingType = cursor.getString(cursor.getColumnIndexOrThrow("tipo_de_recheio"))
                val cheeseWeight = cursor.getString(cursor.getColumnIndexOrThrow("peso_do_queijo"))
                val customerCPF = cursor.getString(cursor.getColumnIndexOrThrow("id_cliente"))
                val tamaleOrder = TamaleOrder(fillingType, cheeseWeight.toFloat(), customerCPF, tamaleOrderId.toInt())
                tamaleOrderList.add(tamaleOrder)
            }while(cursor.moveToNext())
        }

        cursor.close()

        return tamaleOrderList
    }

    fun update(tamaleOrder: TamaleOrder): Boolean {
        val db = this.myDataBase.writableDatabase;
        val cv = ContentValues().apply {
            put("tipo_de_recheio", tamaleOrder.fillingType);
            put("peso_do_queijo", tamaleOrder.cheeseWeight);
        }

        Log.i("Customer Data", " -> " + tamaleOrder)

        val affectedRows = db.update("pedido_pamonha", cv, "id_pedido_pamonha = ?", arrayOf(tamaleOrder.id.toString()));

        Log.i("Linhas afetadas", " -> " + affectedRows)

        db.close();

        return affectedRows > 0;
    }

    fun delete(tamaleOrderId: Int): Boolean {
        val db = myDataBase.writableDatabase
        val affectedRows = db.delete("pedido_pamonha", "id_pedido_pamonha = ?", arrayOf(tamaleOrderId.toString()))
        db.close()

        return affectedRows > 0
    }
}