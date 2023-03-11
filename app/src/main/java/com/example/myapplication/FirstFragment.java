package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class FirstFragment extends AppCompatActivity {
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
        letter_position += 1;
        TextView txtHello = findViewById(R.id.txtMsg);
        txtHello.setText(letters[letter_position]);
        speak(letters[letter_position]);
        if (letter_position == 25) {
            letter_position = -1;
        }
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