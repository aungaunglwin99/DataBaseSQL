package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val list : ArrayList<StudentModel>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_student, parent, false)
        return StudentViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        val item = list[position]

        holder.tvName.text = item.name
        holder.tvGrade.text = item.grade
        if (item.gender == "Male") {
            holder.ivGender.setImageResource(R.drawable.baseline_male_24)
        } else {
            holder.ivGender.setImageResource(R.drawable.baseline_female_24)
        }

        // Edit button click
        holder.ivEdit.setOnClickListener {
            // TODO later
        }

        // Delete button click
        holder.ivDelete.setOnClickListener {
            // TODO later
        }
    }
    override fun getItemCount() = list.size
    class StudentViewHolder(val v : View) : RecyclerView.ViewHolder(v){
        val tvName : TextView = v.findViewById(R.id.tvName)
        val tvGrade : TextView = v.findViewById(R.id.tvGrade)
        val ivGender : ImageView = v.findViewById(R.id.ivGender)
        val ivEdit : ImageView = v.findViewById(R.id.ivEdit)
        val ivDelete : ImageView = v.findViewById(R.id.ivDelete)
    }
}