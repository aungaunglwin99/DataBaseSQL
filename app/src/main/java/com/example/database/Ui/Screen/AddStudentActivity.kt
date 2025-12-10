package com.example.database.Ui.Screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.database.Local.StudentDataBase
import com.example.database.R

class AddStudentActivity : AppCompatActivity() {
    lateinit var etName : EditText
    lateinit var etRoomNo : EditText
    lateinit var etGrade : EditText

    lateinit var etPName : EditText
    lateinit var btAdd : Button

    lateinit var rbMale: RadioButton
    lateinit var rbFemale: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        etName = findViewById(R.id.etName)
        etRoomNo = findViewById(R.id.etRoomNo)
        etGrade = findViewById(R.id.etGrade)
        etPName = findViewById(R.id.etPName)
        btAdd = findViewById(R.id.btAdd)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)

        btAdd.setOnClickListener {
            val name = etName.text.toString()
            val grade = etGrade.text.toString()
            val roomNo = etRoomNo.text.toString().toIntOrNull() ?: 0
            val father = etPName.text.toString()
            val gender = if (rbMale.isChecked) "Male" else "Female"

            val db = StudentDataBase(this)
            db.insertStudent(name, grade, roomNo, gender, father)

            finish() // return to MainActivity
        }

    }
}