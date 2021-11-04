package com.example.test
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test.data.ParticipantViewModel


class ExportData : AppCompatActivity() {

    private lateinit var mParticipantViewModel: ParticipantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_data)


        val projectName: EditText = findViewById(R.id.projectQuery)
        val begin: Button = findViewById(R.id.exitExport)
        val export: Button = findViewById(R.id.export)
        val delete: Button = findViewById(R.id.deleteData)
        //ParticipantViewModel
        mParticipantViewModel = ViewModelProvider(this).get(ParticipantViewModel::class.java)


        begin.setOnClickListener{
            toMain()
        }
        export.setOnClickListener{
            exportData()
        }

        delete.setOnClickListener{
            deleteData()
        }


    }


    private fun toMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    val CREATE_FILE = 0
    var write = ""

    private fun exportData() {
        write = "Id, Participant, Project, Trial, Correctly Answered, Incorrectly Answered, Total Answered, Accuracy Rate, Error Rate \n"
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply{
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/csv"
            putExtra(Intent.EXTRA_TITLE, "Nate")
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, "")
        }
        startActivityForResult(intent, CREATE_FILE)

        mParticipantViewModel.readAllData.observe(this, Observer { list ->
            val listIterator = list.iterator()
            list.forEach{
                write = write + it.id + ", "
                write = write + it.participant + ", "
                write = write + it.project + ", "
                write = write + it.trial + ", "
                write = write + it.correctlyAnswered + ", "
                write = write + it.incorrectlyAnswered + ", "
                write = write + it.totalAnswered + ", "
                write = write + it.accuracyRate + ", "
                write = write + it.errorRate + "\n"
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CREATE_FILE && resultCode == RESULT_OK){
            val uri = data!!.data
            try{
                val outputStream = this.contentResolver.openOutputStream(uri!!)
                outputStream?.write(write.toByteArray())
                outputStream?.close()
            } catch (e:Exception){
                print(e.localizedMessage)
            }
        }
    }



    private fun deleteData(){
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do you want to clear all Data?")
            .setCancelable(false)
            .setPositiveButton("Delete All") { _, _ ->
                mParticipantViewModel.nukeAll()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Delete All Data")
        // show alert dialog
        alert.show()
    }
}
