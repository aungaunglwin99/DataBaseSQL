package com.example.database.Ui.Screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Adapter.StudentAdapter
import com.example.database.Local.StudentDataBase
import com.example.database.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvStudent: RecyclerView
    lateinit var fbAdd: FloatingActionButton
    lateinit var studentDataBase: StudentDataBase
    lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvStudent = findViewById(R.id.rvStudent)
        fbAdd = findViewById(R.id.fbAdd)
        studentDataBase = StudentDataBase(this)

        adapter = StudentAdapter(studentDataBase.getAllStudents())
        rvStudent.adapter = adapter

        fbAdd.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, AddStudentActivity::class.java)
            )

        }

    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(studentDataBase.getAllStudents())
    }

}