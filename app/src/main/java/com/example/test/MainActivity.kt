package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R.id.exportData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val participant: EditText = findViewById(R.id.participantName)
        val project: EditText = findViewById(R.id.projectName)
        val trial: EditText = findViewById(R.id.trialName)
        val begin = findViewById<Button>(R.id.submit)
        val export = findViewById<Button>(exportData)

        //Functions for going between activities
        fun startTest(){
            val intent = Intent(this, DisplayTestActivity::class.java)
            intent.putExtra("ParticipantID", participant.text.toString())
            intent.putExtra("Project", project.text.toString())
            intent.putExtra("Trial", trial.text.toString())
            startActivity(intent)
        }

        fun startExport(){
            val intent = Intent(this, ExportData::class.java)
            startActivity(intent)
        }


        //onClick Listeners
        begin.setOnClickListener{
            startTest()
        }
        export.setOnClickListener{
            startExport()
        }


    }
}