package com.example.database.Ui.Screen

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.database.Adapter.StudentAdapter
import com.example.database.Local.StudentDataBase
import com.example.database.Model.StudentModel
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var studentDataBase: StudentDataBase
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentDataBase = StudentDataBase(this)

        binding.apply {
            tbInclude.tbMain.title = "Students"

            adapter = StudentAdapter(
                list = studentDataBase.getAllStudents(),
                onEditClick = { student -> editStudent(student) },
                onDeleteClick = { student ->
                    deleteStudent(
                        context = this@MainActivity,
                        student = student
                    )
                }
            )
            rvStudent.adapter = adapter

            fbAdd.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddStudentActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list every time the activity resumes
        adapter.updateList(studentDataBase.getAllStudents())
    }

    // Open edit screen with student data
    private fun editStudent(student: StudentModel) {

        val intent = Intent(this, AddStudentActivity::class.java)
        val studentModel = StudentModel(
            student.studentId,
            student.name,
            student.grade,
            student.roomNo,
            student.gender,
            student.fatherName,
        )
        intent.putExtra("student", studentModel)
        startActivity(intent)
    }

    // Delete student from database and update the list
    private fun deleteStudent(context: Context, student: StudentModel) {
        AlertDialog.Builder(context)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete ${student.name}?")
            .setPositiveButton("Yes") { dialog, _ ->
                studentDataBase.deleteStudent(student.studentId)
                adapter.updateList(studentDataBase.getAllStudents())
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }
}
