package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class StopWatch : AppCompatActivity() {

    private var isRunning = false
    private var timeInSeconds = 0L
    private val handler = Handler()

    private lateinit var stopwatchDisplay: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var backButton: ImageView

    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                timeInSeconds++
                updateStopwatchDisplay()
                handler.postDelayed(this, 1000)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stop_watch)
//
        stopwatchDisplay = findViewById(R.id.stopwatchDisplay)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
//        backButton = findViewById(R.id.imageView4) // Back to MainActivity

        startButton.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                handler.post(runnable)
            }
        }

        stopButton.setOnClickListener {
            isRunning = false
        }

        resetButton.setOnClickListener {
            isRunning = false
            timeInSeconds = 0L
            updateStopwatchDisplay()
        }

//        backButton.setOnClickListener {
//            val intent2 = Intent(this, MainActivity::class.java)
//            startActivity(intent2)
//        }
    }

    private fun updateStopwatchDisplay() {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60

        val timeString = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
        stopwatchDisplay.text = timeString
    }
}
