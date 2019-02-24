package com.varunsaini.android.noteskeeper.ui.activities;

import com.varunsaini.android.noteskeeper.R;

public class VoiceNoteActivity extends BaseActivity {


    @Override
    int getContentViewId() {
        return R.layout.activity_voice_note;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_voice;
    }
}
