package com.example.meritumsoft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.meritumsoft.Player
import com.example.meritumsoft.R

class PlayersAdapter(
    private var context: Context,
    private val playersList: ArrayList<Player>,
    private val listener: OnListClickedItemListener
) : RecyclerView.Adapter<PlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.player_item, parent, false)
        return PlayersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(playersList[position])
        holder.ivPlayerImage.setOnClickListener {
            listener.onListSelected(position)
        }
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    interface OnListClickedItemListener {
        fun onListSelected(position: Int)
    }
}

class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvPlayerName: TextView = itemView.findViewById(R.id.tv_player_name)
    val ivPlayerImage: ImageView = itemView.findViewById(R.id.iv_player_image)

    fun bind(player: Player) {
        val playerNameFound = player.playerData.find {
            it.termID == "team_field_name_surname"
        }

        if (playerNameFound != null) {
            tvPlayerName.text = formatName(playerNameFound.value)
        }

        if (player.picture != "") {
            Glide.with(tvPlayerName.context)
                .load(player.picture)
                .fitCenter()
                .circleCrop()
                .transition(DrawableTransitionOptions().crossFade())
                .into(ivPlayerImage)
        } else {
            Glide.with(tvPlayerName.context)
                .load(R.drawable.no_image)
                .fitCenter()
                .circleCrop()
                .transition(DrawableTransitionOptions().crossFade())
                .into(ivPlayerImage)
        }
    }

    private fun formatName(name: String): String {
        val firstLetter = name.substring(0, 1)
        val playerName = name.split(' ')
        val playerSurname = playerName[1]
        return firstLetter + ". " + playerSurname.uppercase()
    }
}
