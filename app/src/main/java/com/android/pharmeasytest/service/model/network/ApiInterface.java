package com.android.pharmeasytest.service.model.network;


import com.android.pharmeasytest.service.model.model.ReqresResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("api/users")
    retrofit2.Call<ReqresResponse> getUserList(@Query("page") int page);
}
