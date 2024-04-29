package com.example.provapdm

import android.content.Context
import java.lang.StringBuilder

class BackupDAO(context: Context) {

    private val myDataBase =  MiiBaoDB(context);

    fun create(): String {
        val db = myDataBase.readableDatabase
        val data = StringBuilder()

        val cursorCustomer = db.rawQuery("SELECT * FROM cliente", null)

        data.append("Clientes: \n\n")
        if(cursorCustomer.moveToFirst()) {
            do {
                data.append("CPF: " + cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("cpf")) + "\n")
                data.append("Nome: " + cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("nome")) + "\n")
                data.append("Telefone: " + cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("telefone")) + "\n")
                data.append("E-mail: " + cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("email")) + "\n")
                data.append("Ativo: " + cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("ativo")) + "\n")
                data.append("\n")
            } while(cursorCustomer.moveToNext())
        }

        cursorCustomer.close()

        val cursorTamaleOrder = db.rawQuery("SELECT * FROM pedido_pamonha", null)

        data.append("\nPedidos: \n\n")
        if (cursorTamaleOrder.moveToFirst()) {
            do {
                data.append("Id do pedido: " + cursorTamaleOrder.getString(cursorTamaleOrder.getColumnIndexOrThrow("id_pedido_pamonha")) + "\n")
                data.append("Tipo de recheio: " + cursorTamaleOrder.getString(cursorTamaleOrder.getColumnIndexOrThrow("tipo_de_recheio")) + "\n")
                data.append("Peso do queijo: " + cursorTamaleOrder.getString(cursorTamaleOrder.getColumnIndexOrThrow("peso_do_queijo")) + "\n")
                data.append("CPF do cliente: " + cursorTamaleOrder.getString(cursorTamaleOrder.getColumnIndexOrThrow("id_cliente")) + "\n\n")
            }while(cursorTamaleOrder.moveToNext())
        }

        cursorTamaleOrder.close()
        db.close()
        return data.toString()
    }
}