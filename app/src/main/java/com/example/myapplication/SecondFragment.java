package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import java.util.Locale;

public class SecondFragment extends AppCompatActivity {
    private TextToSpeech mTTS;
    static String[] letters = {
            "A","B","C","D","E",
            "F","G","H","I","J",
            "K","L","M","N","O",
            "P","Q","R","S","T",
            "U","V","W","X","Y","Z"};
    int letter_position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.CANADA);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e( "TTS", "Language not supported");
                    }

                    speak("A");
                } else {
                    Log.e( "TTS", "Initialization failed");
                }
            }
        });
    }

    public void onClick (View view) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(25) + 1;
        TextView txtHello = findViewById(R.id.txtMsg);
        txtHello.setText(letters[randomNumber]);
        speak(letters[randomNumber]);
    }

    public void speak(String letter) {
        String text = letter;

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

}