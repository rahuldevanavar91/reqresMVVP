package com.android.pharmeasytest.service.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.pharmeasytest.service.model.model.Data;
import com.android.pharmeasytest.service.model.model.ReqresResponse;
import com.android.pharmeasytest.service.model.network.ApiClient;
import com.android.pharmeasytest.service.model.network.ApiInterface;
import com.android.pharmeasytest.view.adapter.UserListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReqresResponseRepository {


    private static ReqresResponseRepository mReqresResponseRepository;
    private final ApiInterface mApiService;
    private List<Data> mUserDataList;

    public ReqresResponseRepository() {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        mUserDataList = new ArrayList<>();

    }

    public synchronized static ReqresResponseRepository getInstance() {
        if (mReqresResponseRepository == null) {
            if (mReqresResponseRepository == null) {
                mReqresResponseRepository = new ReqresResponseRepository();
            }
        }
        return mReqresResponseRepository;
    }

    public LiveData<ReqresResponse> getUserList(int page) {
        final MutableLiveData<ReqresResponse> data = new MutableLiveData<>();

        mApiService.getUserList(page).enqueue(new Callback<ReqresResponse>() {
            @Override
            public void onResponse(Call<ReqresResponse> call, Response<ReqresResponse> response) {
                ReqresResponse reqresResponse = response.body();
                removeMoreLoading();
                if (reqresResponse != null) {
                    reqresResponse.setStatus(1);
                    updateData(reqresResponse);
                }
                reqresResponse.setData(mUserDataList);
                data.setValue(reqresResponse);
            }

            @Override
            public void onFailure(Call<ReqresResponse> call, Throwable t) {
                ReqresResponse error = new ReqresResponse();
                error.setStatus(0);
                if (t instanceof IOException) {
                    error.setErrorMessage("Network failure");
                } else {
                    error.setErrorMessage("Something went wrong");
                }
            }
        });
        return data;
    }

    private void removeMoreLoading() {
        if (!mUserDataList.isEmpty() &&
                mUserDataList.get(mUserDataList.size() - 1).getViewType() ==
                        UserListAdapter.VIEW_TYPE_LOAD_MORE) {
            mUserDataList.remove(mUserDataList.size() - 1);
        }

    }

    private void updateData(ReqresResponse reqresResponse) {

        if (reqresResponse.getData() != null) {
            mUserDataList.addAll(reqresResponse.getData());
            if (mUserDataList.size() != reqresResponse.getTotal()) {
                Data loadMore = new Data();
                loadMore.setViewType(UserListAdapter.VIEW_TYPE_LOAD_MORE);
                mUserDataList.add(loadMore);
            }
        }
    }
}
