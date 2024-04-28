package com.example.provapdm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiiBaoDB(context: Context) : SQLiteOpenHelper(context, "mii_bao.db", null, 1) {
    companion object {
        const val CUSTOMER_TABLE = "cliente"
        const val CPF = "cpf"
        const val NAME = "nome"
        const val PHONE = "telefone"
        const val EMAIL = "email"
        const val IS_ACTIVE = "ativo"

        const val TAMALE_ORDER_TABLE = "pedido_pamonha"
        const val ID_TAMALE = "id_pedido_pamonha"
        const val FILLING_TYPE = "tipo_de_recheio"
        const val CHEESE_WEIGHT = "peso_do_queijo"
        const val ID_CUSTOMER = "id_cliente"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createCustomerTable = "CREATE TABLE $CUSTOMER_TABLE" +
                "($CPF TEXT PRIMARY KEY NOT NULL, " +
                "$NAME TEXT, " +
                "$PHONE TEXT, " +
                "$EMAIL TEXT," +
                "$IS_ACTIVE INTEGER DEFAULT 1)"

        val createTamaleOrderTable = "CREATE TABLE $TAMALE_ORDER_TABLE" +
                "($ID_TAMALE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "$FILLING_TYPE TEXT, " +
                "$CHEESE_WEIGHT FLOAT, " +
                "$ID_CUSTOMER TEXT NOT NULL," +
                "FOREIGN KEY ($ID_CUSTOMER) REFERENCES $CUSTOMER_TABLE($CPF))"


        db.execSQL(createCustomerTable)
        db.execSQL(createTamaleOrderTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        val deleteCustomerTable = "DROP TABLE IF EXISTS $CUSTOMER_TABLE"
        db.execSQL(deleteCustomerTable)

        val deleteTamaleOrderTable = "DROP TABLE IF EXISTS $TAMALE_ORDER_TABLE"
        db.execSQL(deleteTamaleOrderTable)

        onCreate(db)
    }
}