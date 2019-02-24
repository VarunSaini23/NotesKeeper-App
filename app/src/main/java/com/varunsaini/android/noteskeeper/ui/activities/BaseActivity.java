package com.varunsaini.android.noteskeeper.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.varunsaini.android.noteskeeper.R;
import com.varunsaini.android.noteskeeper.ui.ViewModels.AllNotesViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStores;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
        Log.d("asa", "onNavigationItemSelected: "+item.getTitle());
            if (itemId == R.id.action_all) {
                startActivity(new Intent(this, AllNotesActivity.class));
            } else if (itemId == R.id.action_voice) {
                startActivity(new Intent(this, VoiceNoteActivity.class));
            } else if (itemId == R.id.action_account) {
                startActivity(new Intent(this, AccountActivity.class));
            }
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}


}
