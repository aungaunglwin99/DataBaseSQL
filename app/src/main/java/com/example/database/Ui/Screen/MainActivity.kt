package com.example.database.Ui.Screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Adapter.StudentAdapter
import com.example.database.Local.StudentDataBase
import com.example.database.R
import com.example.database.Model.StudentModel
import com.example.database.Ui.Screen.AddStudentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvList: RecyclerView
    lateinit var fButton: FloatingActionButton

    lateinit var studentDataBase: StudentDataBase
    var listItem = ArrayList<StudentModel>()
    lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rvList)
        fButton = findViewById(R.id.fButton)
        studentDataBase= StudentDataBase(this)

        rvList.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(listItem)
        rvList.adapter = adapter

        fButton.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, AddStudentActivity::class.java)
            )

        }

    }
    override fun onResume() {
        super.onResume()
        listItem = studentDataBase.getAllStudents()
        adapter.updateList(listItem)
    }

}