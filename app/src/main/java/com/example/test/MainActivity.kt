package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val participant: EditText = findViewById(R.id.participantName)
        val begin = findViewById<Button>(R.id.submit)

        participant.setText("MaskParticipant")

        fun startTest(){
            val intent = Intent(this, DisplayTestActivity::class.java)
            intent.putExtra("ParticipantID", participant.text.toString())
            startActivity(intent)
        }


        begin.setOnClickListener{
            startTest()
        }


    }
}