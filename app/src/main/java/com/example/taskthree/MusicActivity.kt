package com.example.taskthree

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.taskthree.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var binding: ActivityMusicBinding
    lateinit var handler: Handler
    var favorite = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Prepare Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.fady_shewaya)
        //Prepare SeekBar
        binding.seekBar.max = mediaPlayer.duration
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { mediaPlayer.seekTo(it) }
            }
        })
        // Start Media Player
        mediaPlayer.start()
        // Set Duration Of Song and Appear it in Text
        binding.songDuration.text = timeFormat(mediaPlayer.duration)
        // Set Timer To See The Progress Of The Song
        handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (mediaPlayer != null){
                    binding.songPosition.text = timeFormat(mediaPlayer.currentPosition)
                    handler.postDelayed(this, 1000)
                }

            }
        }, 1000)


        // Button To Pause And Play Song
        binding.playPause.setOnClickListener(View.OnClickListener {
            if (mediaPlayer.isPlaying) {
                binding.playPause.setImageResource(R.drawable.play_icon)
                mediaPlayer.pause()

            } else {
                binding.playPause.setImageResource(R.drawable.ic_baseline_pause_24)
                mediaPlayer.start()
            }
        })
        // Button To Reset Song
        binding.replay.setOnClickListener {
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(this, R.raw.fady_shewaya)
            binding.playPause.setImageResource(R.drawable.play_icon)
        }
        //
        mediaPlayer.setOnCompletionListener {
            binding.playPause.setImageResource(R.drawable.play_icon)
            binding.seekBar.progress = 0
        }
        // Favorite Button
        binding.favorite.setOnClickListener {
            favorite = if (favorite == 0){
                binding.favorite.setImageResource(R.drawable.favorite)
                1
            }else{
                binding.favorite.setImageResource(R.drawable.heart)
                0
            }

        }
    }

    private fun timeFormat(duration: Int): String {
        var time = ""
        var minute = duration / 1000 / 60
        var sec = duration / 1000 % 60
        time = "$minute:"
        if (sec < 10)
            time += "0$sec"
        else
            time += "$sec"
        return time
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.reset()
    }
}