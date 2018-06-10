package com.android.pharmeasytest.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.pharmeasytest.R;
import com.android.pharmeasytest.databinding.ActivityUserDetailsBinding;
import com.android.pharmeasytest.service.model.model.Data;

public class UserDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserDetailsBinding detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);

        setActionBar();
        if (getIntent().getExtras() != null) {
            detailsBinding.setDataBind((Data) getIntent().getParcelableExtra("user_data"));
        }

    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
