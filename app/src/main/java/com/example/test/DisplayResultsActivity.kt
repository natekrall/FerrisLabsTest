package com.example.test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test.data.Participant
import com.example.test.data.ParticipantViewModel

class DisplayResultsActivity : AppCompatActivity() {
    private lateinit var mParticipantViewModel: ParticipantViewModel
    private lateinit var pId: String
    private lateinit var proj: String
    private lateinit var tri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_results)

        mParticipantViewModel = ViewModelProvider(this).get(ParticipantViewModel::class.java)
        //View Values
        val participant = findViewById<TextView>(R.id.participant)
        val attempted = findViewById<TextView>(R.id.pAttempted)
        val correctly = findViewById<TextView>(R.id.pCorrectly)
        val pErrors = findViewById<TextView>(R.id.errorNum)
        val pAccuracy = findViewById<TextView>(R.id.accuracyPercent)
        val pErrorPercent = findViewById<TextView>(R.id.errorPercent)
        val exit = findViewById<Button>(R.id.exitResults)

        //Variables for DB
        pId = intent.getStringExtra("ParticipantID").toString()
        proj = intent.getStringExtra("Project").toString()
        tri = intent.getStringExtra("Trial").toString()
        //Variables for screen
        val attemptedInt = intent.getIntExtra("qAttempted", 0)
        val correctedInt = intent.getIntExtra("qCorrectly", 0)
        val pErrorsInt = attemptedInt - correctedInt
        val pAccuracyF = (correctedInt.toFloat()/attemptedInt*100)
        val pErrorPercentF = 100-pAccuracyF
        val pAccuracyStr = "$pAccuracyF%"
        val pErrorPercentStr = "$pErrorPercentF%"


        participant.text = pId
        attempted.text = attemptedInt.toString()
        correctly.text = correctedInt.toString()
        pErrors.text = pErrorsInt.toString()
        pAccuracy.text = pAccuracyStr
        pErrorPercent.text = pErrorPercentStr


        fun returnHome(){
            insertDataToDatabase(correctedInt, pErrorsInt, attemptedInt, pAccuracyF, pErrorPercentF)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        exit.setOnClickListener{
            returnHome()
        }
    }
    private fun insertDataToDatabase(correctlyAnswered: Int,
                                     incorrectlyAnswered: Int,
                                     totalAnswered: Int,
                                     accuracyRate: Float,
                                     errorRate: Float) {
        if (inputCheck(pId, proj, tri) == true){
            val participant = Participant(0, pId, proj, tri, correctlyAnswered, incorrectlyAnswered, totalAnswered, accuracyRate, errorRate)
            mParticipantViewModel.addParticipant(participant)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Not Successful!", Toast.LENGTH_LONG).show()
        }


    }
    private fun inputCheck(participant: String, project: String, trial: String): Boolean {
        return !(TextUtils.isEmpty(participant) && TextUtils.isEmpty(project) && TextUtils.isEmpty(trial))
    }

}