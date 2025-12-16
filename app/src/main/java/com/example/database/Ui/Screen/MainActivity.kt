package com.example.database.Ui.Screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Adapter.StudentAdapter
import com.example.database.Local.StudentDataBase
import com.example.database.Model.StudentModel
import com.example.database.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvStudent: RecyclerView
    private lateinit var fbAdd: FloatingActionButton
    private lateinit var studentDataBase: StudentDataBase
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvStudent = findViewById(R.id.rvStudent)
        fbAdd = findViewById(R.id.fbAdd)
        studentDataBase = StudentDataBase(this)

        rvStudent.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with callbacks for edit and delete
        adapter = StudentAdapter(
            this,
            studentDataBase.getAllStudents(),
            onEditClick = { student -> editStudent(student) },
            onDeleteClick = { student -> deleteStudent(student) }
        )
        rvStudent.adapter = adapter

        fbAdd.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list every time the activity resumes
        adapter.updateList(studentDataBase.getAllStudents())
    }

    // Open edit screen with student data
    private fun editStudent(student: StudentModel) {
        val intent = Intent(this, EditStudentActivity::class.java).apply {
            putExtra("studentId", student.studentId)
            putExtra("name", student.name)
            putExtra("grade", student.grade)
            putExtra("roomNo", student.roomNo)
            putExtra("fatherName", student.fatherName)
            putExtra("gender", student.gender)
        }
        startActivity(intent)
    }

    // Delete student from database and update the list
    private fun deleteStudent(student: StudentModel) {
        studentDataBase.deleteStudent(student.studentId)
        adapter.updateList(studentDataBase.getAllStudents())
    }
}
