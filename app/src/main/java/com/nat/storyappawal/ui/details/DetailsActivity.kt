package com.nat.storyappawal.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nat.storyappawal.R
import com.nat.storyappawal.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Optional: Tambahkan tombol kembali di toolbar
        binding.buttonBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
