package com.example.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
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
            val intent = Intent()

            intent.putExtra("name", etName.text.toString())
            intent.putExtra("grade", etGrade.text.toString())
            intent.putExtra("roomNo", etRoomNo.text.toString())
            intent.putExtra("gender", if (rbMale.isChecked) "Male" else "Female")
            intent.putExtra("fatherName", etPName.text.toString())

            setResult(RESULT_OK, intent)
            finish()

        }

    }
}