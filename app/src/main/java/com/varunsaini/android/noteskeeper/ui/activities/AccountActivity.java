package com.varunsaini.android.noteskeeper.ui.activities;

import com.varunsaini.android.noteskeeper.R;

public class AccountActivity extends BaseActivity {


    @Override
    int getContentViewId() {
        return R.layout.activity_account;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_account;
    }
}
