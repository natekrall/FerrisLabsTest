package com.example.test
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.data.ParticipantDatabase
import com.example.test.data.ParticipantRepository
import com.example.test.data.ParticipantViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException


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
        //mParticipantViewModel = ViewModelProvider(this).get(ParticipantViewModel::class.java)


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


    private fun exportData() {

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
