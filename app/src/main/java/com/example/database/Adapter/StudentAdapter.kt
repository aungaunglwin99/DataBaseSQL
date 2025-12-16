package com.example.database.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.Model.StudentModel

class StudentAdapter(
    private val context: Context,
    private val list: ArrayList<StudentModel>,
    private val onEditClick: (StudentModel) -> Unit,
    private val onDeleteClick: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
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

        holder.ivEdit.setOnClickListener { onEditClick(item) }

        holder.ivDelete.setOnClickListener {
            // Show confirmation dialog
            AlertDialog.Builder(context)
                .setTitle("Delete Student")
                .setMessage("Are you sure you want to delete ${item.name}?")
                .setPositiveButton("Yes") { dialog, _ ->
                    onDeleteClick(item)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount() = list.size

    fun updateList(newList: ArrayList<StudentModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvRoomNo: TextView = itemView.findViewById(R.id.tvRoomNo)
        val ivGender: ImageView = itemView.findViewById(R.id.ivGender)
        val ivEdit: ImageView = itemView.findViewById(R.id.ivEdit)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
    }
}
