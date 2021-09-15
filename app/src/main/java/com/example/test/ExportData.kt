package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExportData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_data)

        val projectName: EditText = findViewById(R.id.projectQuery)
        val begin: Button = findViewById(R.id.exitExport)
        val export: Button = findViewById(R.id.export)

        fun toMain(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun exportData(){
            //Do room query here
        }


        begin.setOnClickListener{
            toMain()
        }
        export.setOnClickListener{
            exportData()
        }
    }
}