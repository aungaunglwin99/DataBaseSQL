package com.example.database.Local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.Model.StudentModel

class StudentDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Student_DB"
        private const val DATABASE_VERSION = 2
        private const val TABLE_STUDENT = "tbl_student"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_STUDENT (
                student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                grade TEXT,
                roomNo INTEGER,
                gender TEXT,
                fatherName TEXT
            )
        """.trimIndent()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // This will run if the database version is incremented
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENT")
        onCreate(db)
    }

    /** Insert a new student */
    fun insertStudent(
        name: String,
        grade: String,
        roomNo: Int,
        gender: String,
        fatherName: String
    ) {
        writableDatabase.use { db ->
            val cv = ContentValues().apply {
                put("name", name)
                put("grade", grade)
                put("roomNo", roomNo)
                put("gender", gender)
                put("fatherName", fatherName)
            }
            db.insert(TABLE_STUDENT, null, cv)
        }
    }
    /** Update an existing student */
    fun updateStudent(
        studentId: Int,
        name: String,
        grade: String,
        roomNo: Int,
        gender: String,
        fatherName: String
    ) {
        writableDatabase.use { db ->
            val cv = ContentValues().apply {
                put("name", name)
                put("grade", grade)
                put("roomNo", roomNo)
                put("gender", gender)
                put("fatherName", fatherName)
            }
            db.update(TABLE_STUDENT, cv, "student_id=?", arrayOf(studentId.toString()))
        }
    }


    /** Delete a student by ID */
    fun deleteStudent(studentId: Int) {
        writableDatabase.use { db ->
            db.delete(
                TABLE_STUDENT,
                "student_id = ?",
                arrayOf(studentId.toString())
            )
        }
    }


    /** Get all students from database */
    fun getAllStudents(): ArrayList<StudentModel> {
        val list = ArrayList<StudentModel>()
        readableDatabase.use { db ->
            val cursor = db.rawQuery("SELECT * FROM $TABLE_STUDENT", null)
            cursor.use {
                if (it.moveToFirst()) {
                    do {
                        val student = StudentModel(
                            studentId = it.getInt(it.getColumnIndexOrThrow("student_id")),
                            name = it.getString(it.getColumnIndexOrThrow("name")),
                            grade = it.getString(it.getColumnIndexOrThrow("grade")),
                            roomNo = it.getInt(it.getColumnIndexOrThrow("roomNo")),
                            gender = it.getString(it.getColumnIndexOrThrow("gender")),
                            fatherName = it.getString(it.getColumnIndexOrThrow("fatherName"))
                        )
                        list.add(student)
                    } while (it.moveToNext())
                }
            }
        }
        return list
    }
}
