package com.example.database.Local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.Model.StudentModel

class StudentDataBase(context: Context) :
    SQLiteOpenHelper(context, "Student_DB", null, 1) {

    private val TBL_STUDENT = "tbl_student"

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TBL_STUDENT(
                student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                grade TEXT,
                roomNo INTEGER,
                gender TEXT,
                fatherName TEXT
            )
        """.trimIndent()

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    /** Insert a new student */
    fun insertStudent(
        name: String,
        grade: String,
        roomNo: Int,
        gender: String,
        fatherName: String
    ) {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put("name", name)
            put("grade", grade)
            put("roomNo", roomNo)
            put("gender", gender)
            put("fatherName", fatherName)
        }

        try {
            db.insert(TBL_STUDENT, null, cv)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    /** Get all students from database */
    fun getAllStudents(): ArrayList<StudentModel> {
        val list = ArrayList<StudentModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TBL_STUDENT", null)

        if (cursor.moveToFirst()) {
            do {
                val student = StudentModel(
                    studentId = cursor.getInt(cursor.getColumnIndexOrThrow("student_id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    grade = cursor.getString(cursor.getColumnIndexOrThrow("grade")),
                    roomNo = cursor.getInt(cursor.getColumnIndexOrThrow("roomNo")),
                    gender = cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                    fatherName = cursor.getString(cursor.getColumnIndexOrThrow("fatherName"))
                )
                list.add(student)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return list
    }

}
