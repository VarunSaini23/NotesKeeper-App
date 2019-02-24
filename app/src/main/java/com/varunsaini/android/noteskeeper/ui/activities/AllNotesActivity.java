package com.varunsaini.android.noteskeeper.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.varunsaini.android.noteskeeper.R;
import com.varunsaini.android.noteskeeper.adapters.AllNotesAdapter;
import com.varunsaini.android.noteskeeper.models.SimpleNote;
import com.varunsaini.android.noteskeeper.ui.ViewModels.AllNotesViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class AllNotesActivity extends BaseActivity {

    private AllNotesViewModel allNotesViewModel;
    private FloatingActionButton addAlarmCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allNotesViewModel = ViewModelProviders.of(this).get(AllNotesViewModel.class);
        addAlarmCard  = findViewById(R.id.addAlarmCard);
        RecyclerView allAlarmsRecyclerView = findViewById(R.id.allAlarmsRecyclerView);
        allAlarmsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        allAlarmsRecyclerView.setHasFixedSize(true);

        allNotesViewModel.getListSimpleNoteLiveData().observe(this, new Observer<List<SimpleNote>>() {
            @Override
            public void onChanged(List<SimpleNote> simpleNotes) {
                allAlarmsRecyclerView.setAdapter(new AllNotesAdapter(simpleNotes,allNotesViewModel));
            }
        });
        addAlarmCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllNotesActivity.this,AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    int getContentViewId() {
        return R.layout.activity_all_notes;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_all;
    }

}
