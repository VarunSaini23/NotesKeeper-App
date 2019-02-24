package com.varunsaini.android.noteskeeper.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varunsaini.android.noteskeeper.R;
import com.varunsaini.android.noteskeeper.models.SimpleNote;
import com.varunsaini.android.noteskeeper.ui.ViewModels.AllNotesViewModel;
import com.varunsaini.android.noteskeeper.ui.activities.AddNoteActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AllNotesAdapter extends RecyclerView.Adapter<AllNotesAdapter.AllNotesHolder> {

    List<SimpleNote> simpleNoteList = new ArrayList<>();
    AssetManager assetManager;
    AllNotesViewModel allNotesViewModel;
    Context context;

    public AllNotesAdapter(List<SimpleNote> simpleNoteList, AllNotesViewModel allNotesViewModel) {
        this.simpleNoteList = simpleNoteList;
        this.allNotesViewModel = allNotesViewModel;
    }

    @NonNull
    @Override
    public AllNotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_alarms,parent,false);
        assetManager = parent.getContext().getAssets();
        context  = parent.getContext();
        return new AllNotesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllNotesHolder holder, int position) {

        int id = simpleNoteList.get(position).getId();
        String title = simpleNoteList.get(position).getTitle();
        String content = simpleNoteList.get(position).getContent();
        String date = simpleNoteList.get(position).getDate();
        String time = simpleNoteList.get(position).getTime();

        Typeface tf = Typeface.createFromAsset(assetManager,"fonts/Karla.ttf");
        Typeface tf1 = Typeface.createFromAsset(assetManager,"fonts/Karla-Bold.ttf");
        holder.title.setTypeface(tf1);
        holder.content.setTypeface(tf);
        holder.dateTime.setTypeface(tf1);

        if(!title.equals("")) {
            holder.title.setText(title);
        }else{
            holder.title.setVisibility(View.GONE);
        }
        holder.content.setText(content);
        holder.dateTime.setText(date  + "," + time);

        holder.allCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AddNoteActivity.class);
                i.putExtra("id", id);
                Activity activity = (Activity) context;
                activity.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return simpleNoteList.size();
    }

    class AllNotesHolder extends RecyclerView.ViewHolder{

        TextView title,content,dateTime;
        CardView allCard;

        public AllNotesHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            dateTime = itemView.findViewById(R.id.dateTime);
            allCard = itemView.findViewById(R.id.allCard);
        }
    }

}
