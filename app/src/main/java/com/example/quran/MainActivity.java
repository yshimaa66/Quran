package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment;

        fragment = new Fragment_Quran();

        FragmentTransaction ft= this.getSupportFragmentManager().beginTransaction();

        ft.add(R.id.fragment_main,fragment);
        ft.addToBackStack(null);
        ft.commit();





    }


    public void changefragment(View view){

        Fragment fragment;



        if(view == findViewById(R.id.sbhabtn)){
            fragment = new Fragment_Sbha();
            FragmentTransaction ft= this.getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.fragment_main,fragment);
            ft.addToBackStack(null);
            ft.commit();

            view.setBackgroundColor(getResources().getColor(R.color.colorselected));
            findViewById(R.id.quranbtn).setBackgroundColor(getResources().getColor(R.color.colornull));
        }

        if(view == findViewById(R.id.quranbtn)){
            fragment = new Fragment_Quran();

            FragmentTransaction ft= this.getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.fragment_main,fragment);
            ft.addToBackStack(null);
            ft.commit();


            view.setBackgroundColor(getResources().getColor(R.color.colorselected));
            findViewById(R.id.sbhabtn).setBackgroundColor(getResources().getColor(R.color.colornull));
        }



    }

    public void PrayerCounter(View view){

        TextView TextViewPrayerCounter= (TextView)findViewById(R.id.TextViewPrayerCounter);


        int NumOfPrayercounter = Integer.parseInt(TextViewPrayerCounter.getText().toString());
        NumOfPrayercounter++;

        TextViewPrayerCounter.setText(NumOfPrayercounter+"");



    }


    public void Clear(View view){

        TextView TextViewPrayerCounter= (TextView)findViewById(R.id.TextViewPrayerCounter);

        TextViewPrayerCounter.setText(0+"");



    }

}
