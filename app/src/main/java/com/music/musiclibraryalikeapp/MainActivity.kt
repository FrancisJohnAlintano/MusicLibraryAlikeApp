package com.music.musiclibraryalikeapp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.music.musiclibraryalikeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaplayer = MediaPlayer.create(this,R.raw.music)
        seekbar.progress = 0
        seekbar.max = mediaplayer.duration

        play_btn.setOnClickListener {
            if(!mediaplayer.isPlaying) {
                mediaplayer.start()
                play_btn.setImageResource(R.drawable.baseline_pause_24)
            }else{
                mediaplayer.pause()
                play_btn.setImageResource(R.drawable.baseline_play_arrow_24)
            }
        }
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        runnable = Runnable{
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaplayer.setOnCompletionListener { MediaPlayer
            play_btn.setImageResource(R.drawable.baseline_play_arrow_24)
            seekbar.progress = 0
        }
    }
}