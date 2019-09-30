package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Surahsbody_List extends AppCompatActivity {

    private String Surahsbody;

    public Surahsbody_List(String surahsbody) {
        Surahsbody = surahsbody;
    }

    public String getSurahsbody() {
        return Surahsbody;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surahsbody__list);
    }
}
