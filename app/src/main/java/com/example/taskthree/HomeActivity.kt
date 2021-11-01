package com.example.taskthree

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskthree.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this,WebActivity::class.java)
        binding.webButton.setOnClickListener {
            intent = Intent(this,WebActivity::class.java)
            startActivity(intent)
        }
        binding.songButton.setOnClickListener {
            intent = Intent(this,MusicActivity::class.java)
            startActivity(intent)
        }
        binding.videoButton.setOnClickListener {
            intent = Intent(this,VideoActivity::class.java)
            startActivity(intent)
        }
    }
}