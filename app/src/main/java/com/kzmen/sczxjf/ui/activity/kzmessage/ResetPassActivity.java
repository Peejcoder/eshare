package com.kzmen.sczxjf.ui.activity.kzmessage;

import android.os.Bundle;

import com.kzmen.sczxjf.R;
import com.kzmen.sczxjf.ui.activity.basic.SuperActivity;

public class ResetPassActivity extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateDataForView() {
        setTitle(R.id.kz_tiltle, "重置密码");
    }
    @Override
    protected boolean isShareActivity() {
        return true;
    }
    @Override
    public void setThisContentView() {
        setContentView(R.layout.activity_reset_pass);
    }
}