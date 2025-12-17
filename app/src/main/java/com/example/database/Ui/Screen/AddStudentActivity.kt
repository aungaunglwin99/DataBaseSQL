package com.example.database.Ui.Screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.database.Local.StudentDataBase
import com.example.database.Model.StudentModel
import com.example.database.R
import com.example.database.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddStudentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = StudentDataBase(this)

        val student = intent.getParcelableExtra<StudentModel>("student")

        binding.apply {
            tbInclude.tbMain.title = if (student != null) "Edit Student" else "Add Students"
            btAdd.text = if (student != null) "Update Student" else "Add Student"

            if (student != null) {
                setExistingData(
                    name = student.name,
                    grade = student.grade,
                    roomNo = student.roomNo.toString(),
                    gender = student.gender,
                    fatherName = student.fatherName
                )
            }
            btAdd.setOnClickListener {
                val name = etName.text.toString()
                val grade = etGrade.text.toString()
                val roomNo = etRoomNo.text.toString().toIntOrNull() ?: 0
                val gender = if (rbMale.isChecked) "Male" else "Female"
                val fatherName = etPName.text.toString()

                if (student != null) {
                    db.updateStudent(
                        studentId = student.studentId,
                        name,
                        grade,
                        roomNo,
                        gender,
                        fatherName
                    )
                } else {
                    db.insertStudent(name, grade, roomNo, gender, fatherName)
                }

                finish()
            }

        }
    }


    @SuppressLint("ResourceType")
    fun setExistingData(
        name: String,
        grade: String,
        roomNo: String,
        gender: String,
        fatherName: String
    ) = with(binding) {
        etName.setText(name)
        etGrade.setText(grade)
        etRoomNo.setText(roomNo)
        etPName.setText(fatherName)
        if (gender == "Male") {
            rgGender.check(rbMale.id)
        } else {
            rgGender.check(rbFemale.id)
        }
    }

}