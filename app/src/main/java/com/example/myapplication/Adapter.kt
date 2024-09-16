package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val layout: LinearLayout = itemView.findViewById(R.id.mylayout)
        val date: TextView = itemView.findViewById(R.id.date)
        val time: TextView = itemView.findViewById(R.id.time)
        val endTime: TextView = itemView.findViewById(R.id.end_time)
        val enddate: TextView = itemView.findViewById(R.id.end_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        // Set the background color based on priority
        when (item.priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#32CD32"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
        }

        // Set the text for each view
        holder.title.text = item.title
        holder.priority.text = item.priority
        holder.date.text = item.Rdate
        holder.time.text = item.Rtime
        holder.enddate.text = item.endDate
        holder.endTime.text = item.endTime

        // Handle click events
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<CardInfo>) {
        data = newData
        notifyDataSetChanged()
    }
}
