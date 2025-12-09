package com.example.database

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvList : RecyclerView
    lateinit var fButton : FloatingActionButton

    var REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     rvList = findViewById(R.id.rvList)
     fButton = findViewById(R.id.fButton)

        fButton.setOnClickListener {
            startActivityForResult(Intent(this, AddStudentActivity::class.java), REQUEST_CODE)

        }



    }

}