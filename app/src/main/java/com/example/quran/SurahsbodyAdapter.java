package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SurahsbodyAdapter extends RecyclerView.Adapter<SurahsbodyAdapter.Viewholder> {

    private List<Surahsbody_List> surahsbodyList;
    private Context context;

    public SurahsbodyAdapter(List<Surahsbody_List> surahsbodyList, Context context) {
        this.surahsbodyList = surahsbodyList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_surahsbody__list,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Surahsbody_List surahsbodylist= surahsbodyList.get(position);

        holder.TextViewSurahbody.setText(surahsbodylist.getSurahsbody());

    }

    @Override
    public int getItemCount() {
        return surahsbodyList.size();
    }





    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView TextViewSurahbody;




        public Viewholder(@NonNull View itemView) {
            super(itemView);

            TextViewSurahbody=(TextView)itemView.findViewById(R.id.TextViewSurahbody);


            /*
            String[] parts = TextViewHead.getName().split(" ");
            String numofs = parts[0];
            */




        }









    }







}
