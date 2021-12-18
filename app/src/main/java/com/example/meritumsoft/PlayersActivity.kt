package com.example.meritumsoft

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meritumsoft.adapter.PlayersAdapter
import com.example.meritumsoft.databinding.ActivityPlayersBinding

class PlayersActivity : AppCompatActivity(), PlayersAdapter.onListClickedItemListener {
    private lateinit var binding: ActivityPlayersBinding
    private var players = arrayListOf<Player>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ivBack.setOnClickListener {
            finish()
        }

        loadProfileImage()
        getPlayersData()

    }

    private fun loadProfileImage() {
        Glide.with(this)
            .load(R.drawable.profile_image)
            .circleCrop()
            .into(binding.profileImage)
    }

    private fun getPlayersData() {
        NetworkCallPlayers.getPlayerData { response ->
            val data = response.category.players

            data.position.forEach { position ->
                players.addAll(position.player)
            }
            setFirstPlayerData(players)

            binding.rvPlayers.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(this@PlayersActivity, RecyclerView.HORIZONTAL, false)
                adapter = PlayersAdapter(this@PlayersActivity, players, this@PlayersActivity)
            }
        }
    }

    override fun onListSelected(position: Int) {
        val findName = players[position].playerData.find {
            it.termID == "team_field_name_surname"
        }
        if (findName != null) {
            splitName(findName.value)
        }

        val findTeamNumber = players[position].playerData.find {
            it.termID == "team_list_number"
        }

        if (findTeamNumber != null) {
            binding.tvPlayerPosition.text = findTeamNumber.value
        }

        if(players[position].picture != "") {
            Glide.with(this)
                .load(players[position].picture)
                .into(binding.ivPlayerImageCard)
        } else {
            Glide.with(this)
                .load(R.drawable.player_image)
                .into(binding.ivPlayerImageCard)
        }

    }

    private fun splitName(name: String) {
        val arrayName = name.split(' ')
        val firstName = arrayName[0]
        val lastName = arrayName[1]
        binding.tvPlayerFirstname.text = firstName
        binding.tvPlayerLastname.text = lastName
    }

    private fun setFirstPlayerData(data: ArrayList<Player>) {
        binding.tvPlayerPosition.text = players[0].playerData[1].value
        splitName(players[0].playerData[0].value)

        if(players[0].picture != "") {
            Glide.with(this)
                .load(players[0].picture)
                .into(binding.ivPlayerImageCard)
        } else {
            Glide.with(this)
                .load(R.drawable.player_image)
                .into(binding.ivPlayerImageCard)
        }
    }
}