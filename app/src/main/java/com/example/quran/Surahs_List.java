package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Surahs_List extends AppCompatActivity {

    private String Surah;

    public Surahs_List(String surah) {
        Surah = surah;
    }

    public String getSurah() {
        return Surah;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surahs__list);
    }
}
