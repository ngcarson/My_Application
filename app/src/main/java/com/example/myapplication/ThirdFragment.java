package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.Locale;


public class ThirdFragment extends AppCompatActivity {

    private TextToSpeech mTTS;
    private int ittNumber;
    private String displayString;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_third);
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
        ittNumber += 1;
        displayString = String.valueOf(ittNumber);
        TextView txtHello = findViewById(R.id.txtMsg);
        txtHello.setText(displayString);
        speak(displayString);

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