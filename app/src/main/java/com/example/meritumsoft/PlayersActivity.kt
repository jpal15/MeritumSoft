package com.example.meritumsoft

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meritumsoft.adapter.PlayersAdapter
import com.example.meritumsoft.databinding.ActivityPlayersBinding

class PlayersActivity : AppCompatActivity(), PlayersAdapter.OnListClickedItemListener {
    private lateinit var binding: ActivityPlayersBinding
    private var players = arrayListOf<Player>()
    private var progressDialog: Dialog? = null
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

    override fun onListSelected(position: Int) {
        setPlayerData(position)
    }

    private fun loadProfileImage() {
        Glide.with(this)
            .load(R.drawable.profile_image)
            .circleCrop()
            .into(binding.profileImage)
    }

    private fun getPlayersData() {
        showCustomProgressDialog()
        NetworkCallPlayers.getPlayerData { response ->
            hideProgressDialog()
            binding.svPlayersData.visibility = View.VISIBLE
            val data = response.category.players

            data.position.forEach { position ->
                players.addAll(position.player)
            }
            setFirstPlayerData()

            binding.rvPlayers.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(this@PlayersActivity, RecyclerView.HORIZONTAL, false)
                adapter = PlayersAdapter(this@PlayersActivity, players, this@PlayersActivity)
            }
        }
    }

    private fun setFirstPlayerData() {
        setPlayerData(0)
    }

    private fun splitName(name: String) {
        val arrayName = name.split(' ')
        val firstName = arrayName[0]
        val lastName = arrayName[1]
        binding.tvPlayerFirstname.text = firstName
        binding.tvPlayerLastname.text = lastName.uppercase()
    }

    private fun setPlayerData(position: Int) {
        val playerNameFound = players[position].playerData.find {
            it.termID == "team_field_name_surname"
        }
        if (playerNameFound != null) {
            splitName(playerNameFound.value)
        }

        val teamNumberFound = players[position].playerData.find {
            it.termID == "team_list_number"
        }

        if (teamNumberFound != null) {
            binding.tvPlayerPosition.text = teamNumberFound.value
        }

        val playerWeightFound = players[position].playerData.find {
            it.termID == "team_field_height"
        }

        if (playerWeightFound != null) {
            if (playerWeightFound.value == "") {
                binding.tvPlayerWeight.text = "N/A"
            } else {
                val dataArray = playerWeightFound.value.split(' ')
                binding.tvPlayerWeight.text = dataArray[0]
            }
        }

        val playerHeightFound = players[position].playerData.find {
            it.termID == "team_field_weight"
        }

        if (playerHeightFound != null) {
            if (playerHeightFound.value == "") {
                binding.tvPlayerHeight.text = "N/A"
            } else {
                binding.tvPlayerHeight.text = playerHeightFound.value
            }
        }

        val playerBirthDateFound = players[position].playerData.find {
            it.termID == "team_birth_date"
        }

        if (playerBirthDateFound != null) {
            if (playerBirthDateFound.value == "") {
                binding.tvDateOfBirth.text = "N/A"
            } else {
                binding.tvDateOfBirth.text = playerBirthDateFound.value
            }
        }

        val playerNationalityFound = players[position].playerData.find {
            it.termID == "team_field_nationality"
        }

        if (playerNationalityFound != null) {
            if (playerNationalityFound.value == "") {
                binding.tvNacionalidadData.text = "N/A"
            } else {
                binding.tvNacionalidadData.text = playerNationalityFound.value
            }
        }

        if (players[position].picture != "") {
            Glide.with(this)
                .load(players[position].picture)
                .into(binding.ivPlayerImageCard)
        } else {
            Glide.with(this)
                .load(R.drawable.player_image)
                .into(binding.ivPlayerImageCard)
        }
    }

    private fun showCustomProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog!!.setContentView(R.layout.dialog_please_wait)
        progressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
    }
}