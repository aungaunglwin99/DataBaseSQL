package com.example.database.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.Model.StudentModel

class StudentAdapter(val list : ArrayList<StudentModel>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_student, parent, false)
        return StudentViewHolder(v)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = list[position]

        holder.tvName.text = item.name
        holder.tvRoomNo.text = item.roomNo.toString()
        holder.ivGender.setImageResource(
            if (item.gender == "Male") R.drawable.baseline_male_24
            else R.drawable.baseline_female_24
        )
    }

    override fun getItemCount() = list.size

    fun updateList(newList: ArrayList<StudentModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }



    class StudentViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val tvName : TextView = v.findViewById(R.id.tvName)
        val tvRoomNo : TextView = v.findViewById(R.id.tvRoomNo)
        val ivGender : ImageView = v.findViewById(R.id.ivGender)
        val ivEdit : ImageView = v.findViewById(R.id.ivEdit)
        val ivDelete : ImageView = v.findViewById(R.id.ivDelete)
    }
}