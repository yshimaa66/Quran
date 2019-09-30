package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Surahsbody extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Surahsbody_List> surahsbodyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surahsbody);



        recyclerView = (RecyclerView) findViewById(R.id.RecycleVSurahsbody);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Surahsbody.this));

        surahsbodyList = new ArrayList<>();


        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("surah_title");




        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray surats=obj.getJSONArray("sura");




            for (int i=0;i<surats.length();i++){
                JSONObject jsonObjec=surats.getJSONObject(i);
                String ayaname=jsonObjec.getString("name");
                JSONArray ayas=jsonObjec.getJSONArray("aya");
                surahsbodyList.add(new Surahsbody_List("سورة "+ayaname));
                String ayaandayanum="";
                if(i != 8 && i !=0){
                    //ayaandayanum+="\n";
                    ayaandayanum+="بسم الله الرحمن الرحيم";
                    ayaandayanum+="\n";
                }


                for (int j=0;j<ayas.length();j++){
                    JSONObject jsonObje=ayas.getJSONObject(j);
                    String aya=jsonObje.getString("text");
                    int ayanum=jsonObje.getInt("index");
                    ayaandayanum+=aya+" {"+ayanum+"} ";

                }
                ayaandayanum+="\n";
                ayaandayanum+=" صدق الله العظيم";
                //ayaandayanum+="\n";
                surahsbodyList.add(new Surahsbody_List(ayaandayanum));





            }
            adapter = new SurahsbodyAdapter(surahsbodyList, this);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition((value*2));//(value*2)+1

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("quran.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }





}
