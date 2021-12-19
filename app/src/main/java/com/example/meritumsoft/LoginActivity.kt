package com.example.meritumsoft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.meritumsoft.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var sliderDotsPanel: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadProfileImage()

        sliderDotsPanel = findViewById(R.id.ll_slider_dots)

        binding.llInicial.setOnClickListener {
            val intent = Intent(this, PlayersActivity::class.java)
            startActivity(intent)
        }

        setDots()
    }

    private fun loadProfileImage() {
        Glide.with(this)
            .load(R.drawable.profile_image)
            .circleCrop()
            .into(binding.ivProfileImage)
    }

    private fun setDots() {
        val dotsCount = 5
        val dots = arrayOfNulls<ImageView>(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotsPanel!!.addView(dots[i], params)
        }
        dots[2]?.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.active_dot
            )
        )
    }
}