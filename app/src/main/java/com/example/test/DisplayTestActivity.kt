package com.example.test

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DisplayTestActivity : AppCompatActivity() {
    private val answers = Questions()
    private val duration: Long = 600000
    var countDown = 10






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_test)
        //Values from Previous Activity
        val participantName = intent.getStringExtra("ParticipantID").toString()
        val project = intent.getStringExtra("Project").toString()
        val trial = intent.getStringExtra("Trial").toString()

        //Initializing Button and Editable
        val btn1 = findViewById<Button>(R.id.back)
        val btn2 = findViewById<Button>(R.id.next)
        val exit = findViewById<Button>(R.id.exit)
        val aEdit = findViewById<EditText>(R.id.participantAnswer)
        val questionText = findViewById<TextView>(R.id.question)
        val cNum = findViewById<TextView>(R.id.countDown)
        questionText.text = answers.newQuestion()

        fun viewResults(){
            //store answer and question
            val intent = Intent(this, DisplayResultsActivity::class.java)
            intent.putExtra("ParticipantID", participantName)
            intent.putExtra("Project", project)
            intent.putExtra("Trial", trial)
            intent.putExtra("qAttempted", answers.getTotalQ())
            intent.putExtra("qCorrectly", answers.correctlyAnswered())
            startActivity(intent)
            finish()
        }

        val timer2 = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                cNum.text = (countDown--).toString()
            }

            override fun onFinish() {
                val text = aEdit.text
                answers.finalStore(text.toString())
                viewResults()
            }
        }

        val timer = object: CountDownTimer(duration-10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                cNum.text = countDown.toString()
                timer2.start()
            }
        }
        timer.start()



        //Pull up Previous Question and answer
        btn1.setOnClickListener {
            val text = aEdit.text
            questionText.text = answers.previousQuestion(text.toString())
            //questionText.text = answers.previousQuestion()
            aEdit.setText(answers.retrieveAnswer())

        }

        //Pull up Next Question and Potentially answer
        btn2.setOnClickListener{
            val text = aEdit.text
            //questionText.text = answers.nextQuestion()
            questionText.text = answers.nextQuestion(text.toString(), aEdit)
            text.clear()
        }

        //Exit to results Screen
        exit.setOnClickListener{
            timer.cancel()
            val text = aEdit.text
            //questionText.text = answers.nextQuestion()
            //questionText.text = answers.nextQuestion(text.toString(), aEdit)
            answers.finalStore(text.toString())
            viewResults()
        }

        //Progress Bar
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = 12000
        val currentProgress = 12000
        ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
            .setDuration(duration)
            .start()

    }




}