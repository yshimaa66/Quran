package com.example.quran;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SurahsAdapter extends RecyclerView.Adapter<SurahsAdapter.Viewholder> {



    private List<Surahs_List> SurahsList;
    private Context context;

    public SurahsAdapter(List<Surahs_List> surahsList, Context context) {
        SurahsList = surahsList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_surahs__list, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {


        Surahs_List surahlist = SurahsList.get(position);

        holder.TextViewSurah.setText(surahlist.getSurah());


        //Here it is simply write onItemClick listener here
        holder.readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, Surahsbody.class);

                intent.putExtra("surah_title", position);
                context.startActivity(intent);


                //intent.putExtra("surah_title",position);

            }
        });



        holder.recitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, recite.class);

                intent.putExtra("surah_title", position);
                context.startActivity(intent);


                //intent.putExtra("surah_title",position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return SurahsList.size();
    }




    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView TextViewSurah;
        public Button readbtn;
        public Button recitebtn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            TextViewSurah = (TextView) itemView.findViewById(R.id.TextViewSurah);
            TextViewSurah = (TextView) itemView.findViewById(R.id.TextViewSurah);
            readbtn = (Button) itemView.findViewById(R.id.readbtn);
            recitebtn = (Button) itemView.findViewById(R.id.recitebtn);

        }
    }
}
