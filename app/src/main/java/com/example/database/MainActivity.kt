package com.example.database

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvList: RecyclerView
    lateinit var fButton: FloatingActionButton

    var REQUEST_CODE = 101
    var listItem = ArrayList<StudentModel>()
    lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rvList)
        fButton = findViewById(R.id.fButton)

        adapter = StudentAdapter(listItem)
        rvList.adapter = adapter

        fButton.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, AddStudentActivity::class.java),
                REQUEST_CODE
            )

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && intent != null) {
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && intent != null) {

                // auto ID
                val newId = listItem.size + 1

                val student = StudentModel(
                    studentId = newId,
                    name = intent.getStringExtra("name") ?: "",
                    grade = intent.getStringExtra("grade") ?: "",
                    roomNo = intent.getStringExtra("room") ?: "",
                    gender = intent.getStringExtra("gender") ?: "",
                    fatherName = intent.getStringExtra("father") ?: ""
                )

                listItem.add(student)
                rvList.adapter?.notifyItemInserted(listItem.size - 1)

                adapter = StudentAdapter(listItem)
                rvList.adapter = adapter
            }
        }
    }
}
