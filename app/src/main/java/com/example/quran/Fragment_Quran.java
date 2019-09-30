package com.example.quran;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Fragment_Quran extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Surahs_List> SurahsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__quran, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.RecycleVSurahs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SurahsList = new ArrayList<>();

        adapter = new SurahsAdapter(SurahsList, getActivity());



        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray Surats=obj.getJSONArray("surat");
            for (int i=0;i<Surats.length();i++){
                JSONObject jsonObject=Surats.getJSONObject(i);
                String name=jsonObject.getString("name");
                int num=jsonObject.getInt("surah");



                SurahsList.add(new Surahs_List( num+" . "+"سورة "+name));
            }
            adapter = new SurahsAdapter(SurahsList, getActivity());
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }



    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("surat.json");
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
