package com.goldball.app.ui.main.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldball.app.R
import com.goldball.app.model.Player

class PlayersAdapter(private val callback: PlayerClickCallback) :
    ListAdapter<Player, PlayerViewHolder>(PlayerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(parent, callback)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlayerDiffUtil : DiffUtil.ItemCallback<Player>() {

    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }

}

class PlayerViewHolder(viewGroup: ViewGroup, private val callback: PlayerClickCallback) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_player, viewGroup, false)
    ) {

    val textView = itemView.findViewById<TextView>(R.id.player_text)

    fun bind(player: Player) {
        itemView.setOnClickListener {
            callback.onPlayerClick(player)
        }
        textView.text = "${player.position}. ${player.name}"

    }
}

interface PlayerClickCallback {
    fun onPlayerClick(player: Player)
}
