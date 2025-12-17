package com.example.database.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class StudentModel(
    val studentId: Int,
    val name: String,
    val grade: String,
    val roomNo: Int,
    val gender: String,
    val fatherName: String
) : Parcelable
