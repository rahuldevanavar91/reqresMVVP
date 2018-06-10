package com.android.pharmeasytest.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.pharmeasytest.R;
import com.android.pharmeasytest.service.model.model.Data;
import com.android.pharmeasytest.service.model.model.ReqresResponse;
import com.android.pharmeasytest.view.adapter.UserListAdapter;
import com.android.pharmeasytest.viewModel.ReqresListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListAdapter.UserListAdapterListener {


    private UserListAdapter mUseDataAdapter;
    private ReqresListViewModel mViewModel;
    private com.android.pharmeasytest.databinding.ActivityMainBinding mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setViewModel();
    }

    private void setViewModel() {
        ReqresListViewModel.Factory factory = new ReqresListViewModel.Factory(
                getApplication());

        mViewModel = ViewModelProviders.of(this, factory)
                .get(ReqresListViewModel.class);

        mBinder.setIsLoading(true);
        observeViewModel(mViewModel);


    }

    private void observeViewModel(ReqresListViewModel viewModel) {
        // Update the list when the data changes

        viewModel.getObservableProject().observe(this, new Observer<ReqresResponse>() {
            @Override
            public void onChanged(@Nullable ReqresResponse data) {
                mBinder.setIsLoading(false);
                if (data != null && data.getStatus() == 1) {
                    setAdapter(data.getData());
                } else {
                    Toast.makeText(getApplicationContext(), data.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAdapter(List<Data> userList) {
        if (mUseDataAdapter != null) {
            mUseDataAdapter.updateList(userList);
        } else {

            mUseDataAdapter = new UserListAdapter(userList, this);
            mBinder.userRecycerList.setAdapter(mUseDataAdapter);
        }
    }


    @Override
    public void onLoadMore() {
        mViewModel.getUser();
    }
}
