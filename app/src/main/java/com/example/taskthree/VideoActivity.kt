package com.example.taskthree

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import com.example.taskthree.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoBinding
    private  var mediaController:MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)
         setUpVideoView()
    }

    private fun setUpVideoView() {
        if (mediaController == null){
            mediaController = MediaController(this)
            mediaController!!.setAnchorView(binding.videoView)
        }
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.burn_it_all_down}"))
        binding.videoView.requestFocus()
        binding.videoView.start()


    }
}