package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FirstFragment extends AppCompatActivity {
    static String[] letters = {"A","B","C","D","E","F"};
    int letter_position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick (View view) {
        letter_position += 1;
        TextView txtHello = findViewById(R.id.txtMsg);
        txtHello.setText(letters[letter_position]);
        if (letter_position == 5) {
            letter_position = -1;
        }
    }

}