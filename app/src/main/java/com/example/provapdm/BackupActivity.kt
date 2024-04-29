package com.example.provapdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class BackupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backup)

        val filePath = intent.getStringExtra("filePath")

        if(filePath != null) {
            openFile(filePath)
        } else {
          Toast.makeText(applicationContext, "Caminho do arquivo não fornecido!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFile(filePath: String) {
        val file = File(filePath)
        try {
            val inputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            }

            bufferedReader.close()
            inputStreamReader.close()
            inputStream.close()

            val textViewContentBackup = findViewById<TextView>(R.id.backupText)
            textViewContentBackup.text = stringBuilder.toString()
        } catch(e: Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Erro ao ler o arquivo!", Toast.LENGTH_SHORT).show()
        }
    }
}