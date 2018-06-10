package com.android.pharmeasytest.view.callback;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.pharmeasytest.service.model.model.Data;
import com.android.pharmeasytest.view.ui.UserDetailActivity;

public class OnClickCallback {
    public void onClick(View view, Data data) {
        Context context = view.getContext();
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("user_data", data);
        context.startActivity(intent);
    }
}
