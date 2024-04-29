package com.example.provapdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private val filePath = "MeuArquivo"
    private var externalFile: File? = null

    private lateinit var buttonCustomer: Button
    private lateinit var buttonOrderTamale: Button
    private lateinit var buttonBackup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCustomer = findViewById(R.id.mainButtonCustomer)
        buttonOrderTamale = findViewById(R.id.mainButtonOrder)
        buttonBackup = findViewById(R.id.mainButtonBackup)

        buttonCustomer.setOnClickListener {
            val intent = Intent(this, CustomerActivity::class.java)
            startActivity(intent)
        }

        buttonOrderTamale.setOnClickListener {
            val intent = Intent(this, TamaleOrderActivity::class.java)
            startActivity(intent)
        }

        buttonBackup.setOnClickListener {
            backupCustomer()
        }

    }

    private fun backupCustomer() {
        val backupDAO = BackupDAO(this)
        val data = backupDAO.create()

        val fileName = "backup.txt"
        externalFile = File(getExternalFilesDir(filePath), fileName)

        try {
            val fileOutputStream = FileOutputStream(externalFile)
            fileOutputStream.write(data.toByteArray())
            fileOutputStream.close()

            val intent = Intent(this, BackupActivity::class.java)
            intent.putExtra("filePath", externalFile!!.absolutePath)
            startActivity(intent)

            Toast.makeText(applicationContext, "Backup salvo com sucesso!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.i("Erro", "Erro ao salvar o arquivo: ${e.message}")
            e.printStackTrace()
        }
    }
}