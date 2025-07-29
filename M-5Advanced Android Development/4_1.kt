package com.example.texttospeechapp

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var inputText: EditText
    private lateinit var speakButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        speakButton = findViewById(R.id.speakButton)

        // Initialize TTS
        tts = TextToSpeech(this, this)

        speakButton.setOnClickListener {
            val text = inputText.text.toString()
            if (text.isNotBlank()) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    override fun onDestroy() {
        // Shutdown TTS engine when activity is destroyed
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
