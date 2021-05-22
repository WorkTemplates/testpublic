package com.template.app.ui.main.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.template.app.R
import com.template.app.model.Data

class DataAdapter(private val callback: ClickCallback) :
    ListAdapter<Data, DataViewHolder>(DataDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(parent, callback)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DataDiffUtil : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}

class DataViewHolder(viewGroup: ViewGroup, private val callback: ClickCallback) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
    ) {

    val textView = itemView.findViewById<TextView>(R.id.player_text)

    fun bind(data: Data) {
        itemView.setOnClickListener {
            callback.onClick(data)
        }
        textView.text = "${data.position}. ${data.name}"

    }
}

interface ClickCallback {
    fun onClick(data: Data)
}
