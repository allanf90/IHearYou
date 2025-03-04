package com.example.ihearyou

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var textToSpeech: TextToSpeech
    private lateinit var mainLayout: LinearLayout
    private lateinit var speechRecognizerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialize speech recognition launcher before setContentView()
        speechRecognizerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intentData ->
                    val spokenText = intentData.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        ?.get(0)?.lowercase(Locale.ROOT) ?: ""

                    when {
                        "blue" in spokenText -> updateUI("Here is the blue screen", Color.BLUE)
                        "red" in spokenText -> updateUI("Here is the red screen", Color.RED)
                        else -> showToast("Please say 'Blue' or 'Red'")
                    }
                }
            }
        }

        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.mainLayout)
        val button = findViewById<Button>(R.id.startListeningButton)

        // ✅ Request microphone permission if not granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO), 1)
        }

        // ✅ Initialize Text-to-Speech safely
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
            } else {
                showToast("Text-to-Speech initialization failed!")
            }
        }

        // Start listening on button click
        button.setOnClickListener {
            startListening()
        }
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Say 'Blue' or 'Red'")
        }
        speechRecognizerLauncher.launch(intent)
    }

    private fun updateUI(response: String, color: Int) {
        if (::textToSpeech.isInitialized) {
            textToSpeech.speak(response, TextToSpeech.QUEUE_FLUSH, null, null)
        }
        mainLayout.setBackgroundColor(color)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
