package com.example.database.Ui.Screen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.database.Local.StudentDataBase
import com.example.database.R

class EditStudentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etRoomNo: EditText
    private lateinit var etGrade: EditText
    private lateinit var etPName: EditText
    private lateinit var btEdit: Button
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton

    private var studentId: Int = 0
    private lateinit var db: StudentDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        etName = findViewById(R.id.etName)
        etRoomNo = findViewById(R.id.etRoomNo)
        etGrade = findViewById(R.id.etGrade)
        etPName = findViewById(R.id.etPName)
        btEdit = findViewById(R.id.btEdit)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)

        db = StudentDataBase(this)

        // Get student data from intent
        studentId = intent.getIntExtra("studentId", 0)
        etName.setText(intent.getStringExtra("name"))
        etGrade.setText(intent.getStringExtra("grade"))
        etRoomNo.setText(intent.getIntExtra("roomNo", 0).toString())
        etPName.setText(intent.getStringExtra("fatherName"))
        val gender = intent.getStringExtra("gender")
        if (gender == "Male") rbMale.isChecked = true else rbFemale.isChecked = true

        // Handle edit button click
        btEdit.setOnClickListener {
            val name = etName.text.toString()
            val grade = etGrade.text.toString()
            val roomNo = etRoomNo.text.toString().toIntOrNull() ?: 0
            val father = etPName.text.toString()
            val selectedGender = if (rbMale.isChecked) "Male" else "Female"

            if (name.isBlank() || grade.isBlank() || father.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            db.updateStudent(studentId, name, grade, roomNo, selectedGender, father)
            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            finish() // go back to MainActivity
        }
    }
}
