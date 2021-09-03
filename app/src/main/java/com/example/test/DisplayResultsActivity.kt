package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayResultsActivity : AppCompatActivity() {



    private fun returnHome(){
        val intent = Intent(this, MainActivity::class.java).apply{
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_results)

        val participant = findViewById<TextView>(R.id.participant)
        val attempted = findViewById<TextView>(R.id.pAttempted)
        val correctly = findViewById<TextView>(R.id.pCorrectly)
        val pErrors = findViewById<TextView>(R.id.errorNum)
        val pAccuracy = findViewById<TextView>(R.id.accuracyPercent)
        val pErrorPercent = findViewById<TextView>(R.id.errorPercent)
        val exit = findViewById<Button>(R.id.exitResults)

        val attemptedInt = intent.getIntExtra("qAttempted", 0)
        val correctedInt = intent.getIntExtra("qCorrectly", 0)
        val pErrorsInt = attemptedInt - correctedInt
        val pAccuracyInt = (correctedInt.toFloat()/attemptedInt*100)
        val pErrorPercentInt = 100-pAccuracyInt
        val pAccuracyStr = "$pAccuracyInt%"
        val pErrorPercentStr = "$pErrorPercentInt%"


        participant.text = intent.getStringExtra("ParticipantID")
        attempted.text = attemptedInt.toString()
        correctly.text = correctedInt.toString()
        pErrors.text = pErrorsInt.toString()
        pAccuracy.text = pAccuracyStr
        pErrorPercent.text = pErrorPercentStr

        exit.setOnClickListener{
            returnHome()
        }
    }

}