package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import java.util.Locale;

public class FourthFragment extends AppCompatActivity {
    private TextToSpeech mTTS;
    static String[] numbers = {
            "1","2","3","4","5",
            "6","7","8","9","10"};
    int number_position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fourth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.CANADA);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e( "TTS", "Language not supported");
                    }

                    speak("1");
                } else {
                    Log.e( "TTS", "Initialization failed");
                }
            }
        });
    }

    public void onClick (View view) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(9) + 1;
        TextView txtHello = findViewById(R.id.txtMsg);
        txtHello.setText(numbers[randomNumber]);
        speak(numbers[randomNumber]);
    }

    public void speak(String number) {
        String text = number;

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