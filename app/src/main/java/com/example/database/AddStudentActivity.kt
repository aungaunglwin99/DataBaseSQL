package com.example.database

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    lateinit var etName : EditText
    lateinit var etRoomNo : EditText
    lateinit var etGrade : EditText

    lateinit var etPName : EditText
    lateinit var btAdd : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        etName = findViewById(R.id.etName)
        etRoomNo = findViewById(R.id.etRoomNo)
        etGrade = findViewById(R.id.etGrade)
        etPName = findViewById(R.id.etPName)
        btAdd = findViewById(R.id.btAdd)

    }
}